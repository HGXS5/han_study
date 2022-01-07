package cn.han.rabbitmq;

import com.rabbitmq.client.*;



import java.io.IOException;


public class CustomerNormal {
    private final String Queue_name = "queue_han";

    public String getQueue_name() {
        return Queue_name;
    }

    public void customerTest() throws Exception {
        ConnectionFactory cf = new ConnectionFactory();//创建连接工厂
        Connection connection = cf.newConnection();//创建连接
        Channel channel = connection.createChannel();//创建通道

        DefaultConsumer df = new DefaultConsumer(channel) {//创建消费端对象
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"utf-8");
                System.out.println(Thread.currentThread().getName()+"接收到的消息为："+message);
            }
        };
        channel.basicConsume(Queue_name, true, df);//自动回复队列应答

    }

}
