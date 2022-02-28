package cn.han.rabbitmq.rabbitmqalls;

import com.rabbitmq.client.*;

import javax.lang.model.element.QualifiedNameable;
import java.io.IOException;

/**
 * 路由模式
 * 生产者将消息发给交换机，由交换机根据routingkey来转发消息到指定的队列
 * 交换机不能与上一个模式交换机相同
 */
public class RabbitMqRouting {
    //队列名称
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    //交换机
    private static final String EXCHANGE_FANOUT_INFORM_DIRECT = "exchange_fanout_inform_direct";
    Channel channel = null;

    //生产者
    public void producerSendMessage() {
        try {
            //获取通道
            channel = RabbitMqConnect.getConnect();
            //声明交换机名称及交换机类型
            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM_DIRECT, BuiltinExchangeType.DIRECT);
            //声明队列
            channel.queueDeclare(QUEUE_INFORM_EMAIL, false, false, false, null);
            channel.queueDeclare(QUEUE_INFORM_SMS, false, false, false, null);

            //交换机与队列绑定
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_FANOUT_INFORM_DIRECT, QUEUE_INFORM_EMAIL);
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_FANOUT_INFORM_DIRECT, QUEUE_INFORM_SMS);

            for (int i = 0; i < 6; i++) {//连续生成6条信息
                //创建消息
                String message = "han" + System.currentTimeMillis();
                //消息发布
                /**
                 * params1：Exchange(交换机)名称，没有指定则使用Default Exchange
                 * params2:routingKey(队列名称),消息的路由key,是用于Exchange将消息转发到指定消息队列
                 * params3:消息包含属性
                 * params4：消息体
                 */
                //往邮箱队列发送消息
                channel.basicPublish(EXCHANGE_FANOUT_INFORM_DIRECT, QUEUE_INFORM_EMAIL, null, message.getBytes("utf-8"));
                //往短信队列发送消息
                channel.basicPublish(EXCHANGE_FANOUT_INFORM_DIRECT, QUEUE_INFORM_SMS, null, message.getBytes("utf-8"));
                System.out.println("send message：" + message);
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
    public void customerAcceptMessageEmail() {

        try {
            //创建通道
            channel = RabbitMqConnect.getConnect();
            //声明交换机名称和类型
            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM_DIRECT, BuiltinExchangeType.DIRECT);
            //声明队列
            channel.queueDeclare(QUEUE_INFORM_EMAIL, false, false, false, null);
            //交换机与队列进行绑定
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_FANOUT_INFORM_DIRECT, QUEUE_INFORM_EMAIL);
            //定义消费方法
            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String exchangeName = envelope.getExchange();
                    System.out.println("交换机名称：" + exchangeName);
                    long deliveryTag = envelope.getDeliveryTag();
                    System.out.println("消息id：" + deliveryTag);
                    String routingKey = envelope.getRoutingKey();
                    System.out.println("队列名称：" + routingKey);
                    String message = new String(body, "utf-8");
                    System.out.println("接收到的消息：" + message);
                }
            };

            //监听消息
            while (true) {
                channel.basicConsume(QUEUE_INFORM_EMAIL, true, consumer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //消费者-短信
    public void customerAcceptMessageSMS() {

        try {
            //创建通道
            channel = RabbitMqConnect.getConnect();
            //声明交换机名称和类型
            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM_DIRECT, BuiltinExchangeType.DIRECT);
            //声明队列
            channel.queueDeclare(QUEUE_INFORM_SMS, false, false, false, null);
            //交换机与队列进行绑定
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_FANOUT_INFORM_DIRECT, QUEUE_INFORM_SMS);
            //定义消费方法
            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String exchangeName = envelope.getExchange();
                    System.out.println("交换机名称：" + exchangeName);
                    long deliveryTag = envelope.getDeliveryTag();
                    System.out.println("消息id：" + deliveryTag);
                    String routingKey = envelope.getRoutingKey();
                    System.out.println("队列名称：" + routingKey);
                    String message = new String(body, "utf-8");
                    System.out.println("接收到的消息：" + message);
                }
            };

            //监听消息
            while (true) {
                channel.basicConsume(QUEUE_INFORM_SMS, true, consumer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
