package cn.han.rabbitmq.rabbitmqalls;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 发布订阅模式
 * 生产者将消息发给broker，由交换机将消息转发绑定此交换机的每个队列，每个绑定此交换机的队列都会接收到消息
 */
public class RabbitMqPublishSubscribe {
    //队列名称
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    //交换机
    private static final String EXCHANGE_FANOUT_INFORM = "exchange_fanout_inform";
    Channel channel = null;

    //生产者
    public void producerSendMessage() {
        try {
            //获取通道
            channel = RabbitMqConnect.getConnect();
            if (channel != null) {//设置交换机名称及类型
                channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
                //声明队列
                channel.queueDeclare(QUEUE_INFORM_EMAIL, false, false, false, null);
                channel.queueDeclare(QUEUE_INFORM_SMS, false, false, false, null);
                //交换机与队列绑定
                channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_FANOUT_INFORM, "");
                channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_FANOUT_INFORM, "");

                //发送消息
                for (int i = 0; i < 6; i++) {
                    //创建消息
                    String message = "han：" + System.currentTimeMillis();
                    //发送消息
                    channel.basicPublish(EXCHANGE_FANOUT_INFORM, "", null, message.getBytes("utf-8"));
                    System.out.println(message);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                RabbitMqConnect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //消费者-邮箱
    public void customerEmailAcceptMessage(){
        try {
            //获取通道
            channel = RabbitMqConnect.getConnect();
            if (channel!=null){
                //声明交换机名称和类型
                channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
                //声明队列
                channel.queueDeclare(QUEUE_INFORM_EMAIL, false, false, false, null);
                //交换机队列绑定
                channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_FANOUT_INFORM, "");
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费者-短信
     */
    public void customerEMSAcceptMessage(){
        try {
            //获取通道
            channel = RabbitMqConnect.getConnect();
            if (channel!=null){
                //声明交换机名称和类型
                channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
                //声明队列
                channel.queueDeclare(QUEUE_INFORM_SMS, false, false, false, null);
                //交换机队列绑定
                channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_FANOUT_INFORM, "");
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
