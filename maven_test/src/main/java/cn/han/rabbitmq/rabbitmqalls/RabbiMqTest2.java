package cn.han.rabbitmq.rabbitmqalls;

import org.junit.Test;
//第二个消费端
public class RabbiMqTest2 {
    RabbitMqWorkQueue rmwq = new RabbitMqWorkQueue();//work queue 工作模式
    RabbitMqPublishSubscribe publishSubscribe = new RabbitMqPublishSubscribe();// publish/subscribe发布订阅模式
    RabbitMqRouting routing = new RabbitMqRouting();//routing路由模式
    RabbitMqTopics topics = new RabbitMqTopics();//统配符模式
    /**
     * 消费者-短信
     */
    @Test
    public void accept(){
//        publishSubscribe.customerEMSAcceptMessage();
//        routing.customerAcceptMessageSMS();
        topics.customerAcceptMessageSMS();
    }
}
