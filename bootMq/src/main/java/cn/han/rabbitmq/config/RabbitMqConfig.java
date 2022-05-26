package cn.han.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitMqConfig {
//    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email_han";
//    public static final String QUEUE_INFORM_SMS = "queue_inform_sms_han";
//    public static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform_han";
//
//    /**
//     * 交换机配置
//     *
//     * @return
//     */
//    @Bean(EXCHANGE_TOPICS_INFORM)
//    public Exchange EXCHANGE_TOPICS_INFORM() {
//        ////durable(true)持久化，消息队列重启后交换机仍然存在
//        Exchange exchange = ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
//        return exchange;
//    }
//
//    /**
//     * 声明队列-短信
//     *
//     * @return
//     */
//    @Bean(QUEUE_INFORM_SMS)
//    public Queue QUEUE_INFORM_SMS() {
//        Queue queue = new Queue(QUEUE_INFORM_SMS);
//        return queue;
//    }
//
//    /**
//     * 声明队列-短信
//     *
//     * @return
//     */
//    @Bean(QUEUE_INFORM_EMAIL)
//    public Queue QUEUE_INFORM_EMAIL() {
//        Queue queue = new Queue(QUEUE_INFORM_EMAIL);
//        return queue;
//    }
//
//    /**
//     * 绑定队列-交换机与短信
//     *
//     * @return
//     */
//    @Bean
//    public Binding BINDING_QUEUE_INFORM_SMS(@Qualifier(QUEUE_INFORM_SMS) Queue queue, @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
//        Binding binding = BindingBuilder.bind(queue).to(exchange).with("inform.#.sms.#").noargs();
//        return binding;
//    }
//
//    /**
//     * 绑定队列-交换机与邮件
//     * @param queue
//     * @param exchange
//     * @return
//     */
//    @Bean
//    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue, @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
//        Binding binding = BindingBuilder.bind(queue).to(exchange).with("inform.#.email.#").noargs();
//        return binding;
//    }

    //交换机的名称
    public static final String EX_ROUTING_CMS_POSTPAGE="ex_routing_han";

    /**
     * 交换机配置使用direct类型----路由模式
     * @return the exchange
     */
    @Bean(EX_ROUTING_CMS_POSTPAGE)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        return ExchangeBuilder.directExchange(EX_ROUTING_CMS_POSTPAGE).durable(true).build();
    }
}
