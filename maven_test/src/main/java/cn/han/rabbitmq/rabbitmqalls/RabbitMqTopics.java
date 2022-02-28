package cn.han.rabbitmq.rabbitmqalls;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 统配符模式
 *
 */
public class RabbitMqTopics {
    //队列名称
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    //交换机
    private static final String EXCHANGE_TOPICS_INFORM="exchange_topics_inform2";

    Channel channel = null;

    //生产者
    public void producerSendMessage(){
        try {
            //获取channel
            channel = RabbitMqConnect.getConnect();
            //声明交换机名称和类型
            channel.exchangeDeclare(EXCHANGE_TOPICS_INFORM, BuiltinExchangeType.TOPIC);
            //声明队列
//            channel.queueDeclare(QUEUE_INFORM_EMAIL, false, false, false, null);
            channel.queueDeclare(QUEUE_INFORM_SMS, false, false, false, null);

            //队列与交换机绑定
            //绑定email通知队列
            channel.queueBind(QUEUE_INFORM_EMAIL,EXCHANGE_TOPICS_INFORM,"inform.#.email.#");
            //绑定sms通知队列
            channel.queueBind(QUEUE_INFORM_SMS,EXCHANGE_TOPICS_INFORM,"inform.#.sms.#");
            //发送消息
            for (int i = 0; i <7 ; i++) {
                //创建消息
                String message = "han" + System.currentTimeMillis();
                //发送邮件消息
                channel.basicPublish(EXCHANGE_TOPICS_INFORM, "inform.email", null, message.getBytes("utf-8"));
                //发送短信消息
                channel.basicPublish(EXCHANGE_TOPICS_INFORM, "inform.sms", null, message.getBytes("utf-8"));

                //发送邮件及短信消息
                channel.basicPublish(EXCHANGE_TOPICS_INFORM, "inform.email.sms", null, message.getBytes("utf-8"));
                System.out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                RabbitMqConnect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //消费者-邮箱
    public void customerAcceptMessageEmail(){
        try {
            //获取channel
            channel = RabbitMqConnect.getConnect();
            //声明交换机名称与交换机类型
            channel.exchangeDeclare(EXCHANGE_TOPICS_INFORM, BuiltinExchangeType.TOPIC);
            //声明队列
            channel.queueDeclare(QUEUE_INFORM_EMAIL, false, false, false, null);
            /**
             * 统配符规则：
             *
             * 中间以“.”分隔。
             *
             * 符号#可以匹配多个词，符号*可以匹配一个词语。
             */
            //绑定通配符，关于email队列
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_TOPICS_INFORM, "inform.#.email.#");

            //定义消费方法
            DefaultConsumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String exchangeName = envelope.getExchange();
                    System.out.println("交换机名称："+exchangeName);
                    long deliveryTag = envelope.getDeliveryTag();
                    System.out.println("消息id："+deliveryTag);
                    String routingKey = envelope.getRoutingKey();
                    System.out.println("队列名称："+routingKey);
                    String message = new String(body, "utf-8");
                    System.out.println("接收到的消息："+message);
                }
            };
            while(true){
                //监听消息队列
                channel.basicConsume(QUEUE_INFORM_EMAIL, true, consumer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //消费者-短信
    public void customerAcceptMessageSMS(){
        try {
            //获取channel
            channel = RabbitMqConnect.getConnect();
            //声明交换机名称与交换机类型
            channel.exchangeDeclare(EXCHANGE_TOPICS_INFORM, BuiltinExchangeType.TOPIC);
            //声明队列
            channel.queueDeclare(QUEUE_INFORM_SMS, false, false, false, null);
            /**
             * 统配符规则：
             *
             * 中间以“.”分隔。
             *
             * 符号#可以匹配多个词，符号*可以匹配一个词语。
             */

            //绑定通配符，关于sms队列
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_TOPICS_INFORM, "inform.#.sms.#");

            //定义消费方法
            DefaultConsumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String exchangeName = envelope.getExchange();
                    System.out.println("交换机名称："+exchangeName);
                    long deliveryTag = envelope.getDeliveryTag();
                    System.out.println("消息id："+deliveryTag);
                    String routingKey = envelope.getRoutingKey();
                    System.out.println("队列名称："+routingKey);
                    String message = new String(body, "utf-8");
                    System.out.println("接收到的消息："+message);
                }
            };
            while(true){
                //监听消息队列
                channel.basicConsume(QUEUE_INFORM_SMS, true, consumer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
