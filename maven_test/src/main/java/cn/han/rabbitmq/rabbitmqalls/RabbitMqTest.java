package cn.han.rabbitmq.rabbitmqalls;

import org.junit.Test;

import java.util.concurrent.Executors;
//第一个消费端
public class RabbitMqTest {

    RabbitMqWorkQueue rmwq = new RabbitMqWorkQueue();//work queue 工作模式
    RabbitMqPublishSubscribe publishSubscribe = new RabbitMqPublishSubscribe();// publish/subscribe发布订阅模式
    RabbitMqRouting routing = new RabbitMqRouting();//routing路由模式
    RabbitMqTopics topics = new RabbitMqTopics();//统配符模式

    @Test
    /**
     * 生产者
     */
    public void send() {
//        rmwq.producerSendMessage();
//        publishSubscribe.producerSendMessage();
//    routing.producerSendMessage();
        topics.producerSendMessage();
    }
    @Test
    /**
     * 消费者-邮箱
     */
    public void accept(){
//        rmwq.customerAcceptMessage();
//        publishSubscribe.customerEmailAcceptMessage();
//        routing.customerAcceptMessageEmail();

        topics.customerAcceptMessageEmail();
    }


    //线程实现类
    class ThreadRunnable implements Runnable{
        @Override
        public void run() {
            while (true){
                accept();
            }
        }
    }


}
