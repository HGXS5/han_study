package cn.han.rabbitmq;



import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerOne {

    public static void main(String[] args) throws Exception {
        final String QUEUE_NAME = "queue_han";
            ConnectionFactory cf = new ConnectionFactory();
            Connection connection = cf.newConnection();
            Channel channel = connection.createChannel();

           //声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for (int i = 0; i <12 ; i++) {
                //信息
                String s = "hello"+i;
                //将信息发送到队列
                channel.basicPublish("",QUEUE_NAME,null,s.getBytes("utf-8"));
                System.out.println("发送消息：" + s.getBytes("utf-8"));
                    Thread.sleep(1000);
            }
            channel.close();
            connection.close();

    }
}
