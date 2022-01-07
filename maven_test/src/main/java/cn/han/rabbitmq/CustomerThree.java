package cn.han.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CustomerThree {
    public static void main(String[] args) {
        final String QUEUE_NAME = "queue_han";
        try {
            Connection connection = RabbitMqTest.getConnection();
            Channel channel = connection.createChannel();
            DefaultConsumer df = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String s = new String(body, "utf-8");
                    System.out.println("接收到的消息："+s);
                }
            };
            channel.basicConsume(QUEUE_NAME, true, df);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
