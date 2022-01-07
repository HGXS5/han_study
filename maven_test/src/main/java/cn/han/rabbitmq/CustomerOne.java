package cn.han.rabbitmq;


import com.rabbitmq.client.*;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CustomerOne {

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        final String QUEUE_NAME = "queue_han";
        try {
           connection = RabbitMqTest.getConnection();
            channel = connection.createChannel();
            //同一时刻只会发一条消息给消费者
            channel.basicQos(1);
            //声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            DefaultConsumer df = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    String s = new String(body, "utf-8");
                    System.out.println(Thread.currentThread().getName()+"接受到的信息："+s);
                }
            };
//            DeliverCallback delivery = new DeliverCallback() {
//                public void handle(String consumerTag, Delivery delivery) throws IOException {
//                    String s = new String(delivery.getBody(), "utf-8");
//                    System.out.println("接收到的消息："+s);
//                }
//            };
            //自动回复队列应答
            channel.basicConsume(QUEUE_NAME, true, df);



           // Thread.sleep(1000 * 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
