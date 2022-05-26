package cn.han.msg.core;

import cn.han.msg.handler.CPMessageHandler;
import cn.han.msg.handler.PrintOnlyCPMessageHandler;
import cn.han.msg.util.JdbcTopicDefinitonReader;
import cn.han.msg.util.KafkaClientUtil;
import cn.han.msg.util.ShutdownableThread;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MessageCenter extends ShutdownableThread implements BeanFactoryAware, EnvironmentAware, InitializingBean {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private CPMessageConsumer cpMessageConsumer;

	private CPMessageProducer cpMessageProducer;

	private DefaultListableBeanFactory beanFactory;

	private long lastUpdateTime;

	private Environment env;

	private void initProducer() {
		// @formatter:off
		String clientId = Optional.ofNullable(env.getProperty("kafka.client.id"))
								  .map(x -> x.concat(KafkaClientUtil.generateIdSuffix()))
								  .orElseGet(KafkaClientUtil::getMachineId);
		// @formatter:on

		Properties props = new Properties();
		props.put("bootstrap.servers", env.getProperty("kafka.bootstrap.servers"));
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		props.put("client.id", clientId);

		this.cpMessageProducer = new CPMessageProducer(props);
	}

	private void initConsumer() {
		String groupId = env.getProperty("kafka.group.id");
		String flag = env.getProperty("kafka.topic.query.flag");
		if (StringUtils.isNotBlank(flag))
			groupId = StringUtils.join(groupId, "[", flag, "]");

		Properties props = new Properties();
		props.put("bootstrap.servers", env.getProperty("kafka.bootstrap.servers"));
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
		props.put("enable.auto.commit", env.getProperty("kafka.enable.auto.commit"));
		props.put("auto.commit.interval.ms", env.getProperty("kafka.auto.commit.interval.ms"));
		props.put("session.timeout.ms", env.getProperty("kafka.session.timeout.ms"));
		props.put("group.id", groupId);

		String executorBean = Optional.ofNullable(env.getProperty("kafka.async.executor")).orElse("taskExecutor");
		Optional<Executor> executor = Optional.ofNullable((Executor) beanFactory.getBean(executorBean));

		this.cpMessageConsumer = new CPMessageConsumer(props, executor);
	}

	@Override
	public void afterPropertiesSet() {
		initProducer();
		initConsumer();
		start();
	}

	@Override
	public synchronized void start() {
		initOrUpdateSubscribe();
		super.start();
		lastUpdateTime = System.currentTimeMillis();
	}

	public void initOrUpdateSubscribe() {
		DataSource ds = Optional.ofNullable((DataSource) beanFactory.getBean("dataSource")).orElseThrow(RuntimeException::new);
		List<Topic> topics = new JdbcTopicDefinitonReader(ds).loadTopicDefinitions(env.getProperty("kafka.topic.query.flag"));
		cpMessageConsumer.subscribe(topics.stream().peek(handlerRegister).map(Topic::getName).collect(Collectors.toList()));
	}

	public Consumer<Topic> handlerRegister = topic -> {
		String qualifiedClassName = topic.getHandlerClass();
		if (StringUtils.isBlank(qualifiedClassName))
			qualifiedClassName = PrintOnlyCPMessageHandler.class.getName();

		try {
			Class<?> clz = Class.forName(qualifiedClassName);
			String beanName = StringUtils.uncapitalize(clz.getSimpleName());
			if (!beanFactory.containsBean(beanName)) {
				BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(clz);
				AbstractBeanDefinition beanDefinition = builder.getRawBeanDefinition();
				beanFactory.registerBeanDefinition(beanName, beanDefinition);
			}

			CPMessageHandler handler = (CPMessageHandler) beanFactory.getBean(beanName);
			cpMessageConsumer.registerHandler(topic.getName(), handler);
		} catch (Exception e) {
			logger.error("Error ocurred when create handler bean `{}`", qualifiedClassName);
		}
	};

	@Override
	protected void doWork() {
		if (System.currentTimeMillis() - lastUpdateTime > 2 * 60 * 1000) {
			pauseThenInvoke(() -> initOrUpdateSubscribe());
			lastUpdateTime = System.currentTimeMillis();
		}

		cpMessageConsumer.poll();
	}

	public void send(CPMessage msg) {
		cpMessageProducer.send(msg);
	}

	public void send(CPMessage msg, Callback callback) {
		cpMessageProducer.send(msg, callback);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = (DefaultListableBeanFactory) beanFactory;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

}
