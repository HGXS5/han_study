package cn.han.msg.core;

import cn.han.msg.handler.CPMessageHandler;
import cn.han.msg.handler.PrintOnlyCPMessageHandler;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.*;

class CPMessageConsumer {

	private KafkaConsumer<String, byte[]> consumer;
	private Map<String, CPMessageHandler> handlers = new ConcurrentHashMap<>();
	private CPMessageHandler defaultHandler = new PrintOnlyCPMessageHandler();

	private Executor asyncExecutor;

	private Executor asyncExecutorMgtv;

	{
		// fds 81服务器
		int nThreads = 10;//Runtime.getRuntime().availableProcessors();
		asyncExecutor = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		int mgtvThreads = 6;
		asyncExecutorMgtv = new ThreadPoolExecutor(mgtvThreads, mgtvThreads, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		
	}

	CPMessageConsumer(Properties props) {
		consumer = new KafkaConsumer<>(props);
	}

	CPMessageConsumer(Properties props, Optional<Executor> executor) {
		consumer = new KafkaConsumer<>(props);
//		executor.ifPresent(x -> asyncExecutor = x);
	}

	void subscribe(List<String> topics) {
		consumer.subscribe(topics);
	}

	void registerHandler(String topic, CPMessageHandler handler) {
		handlers.merge(topic, handler, (oldValue, newValue) -> newValue);
	}

	void poll() {
		consumer.poll(1000).forEach(record -> {
			CPMessage msg = SerializationUtils.deserialize(record.value());
			// fds 81服务器
			if ("besTV".equals(msg.getVodCode())) {
				asyncExecutorMgtv.execute(() -> {
					handlers.getOrDefault(msg.getTopic(), defaultHandler).handle(msg);
				});
			} else {
				asyncExecutor.execute(() -> {
					handlers.getOrDefault(msg.getTopic(), defaultHandler).handle(msg);
				});
			}
			
		});
	}

}
