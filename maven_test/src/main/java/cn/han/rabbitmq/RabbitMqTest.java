package cn.han.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class RabbitMqTest {
    String QUEUE_NAME = "queueOne";

    public static void main(String[] args) {
        try {
//            deleteQueueNode("1111");
            customerTest("han");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    static void test() {
        //生产者线程
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    //生产者
                    try {
                        producerTest(i);
                        Thread.sleep(1000 * 10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    static void deleteQueueNode(String name) throws IOException, TimeoutException {
        ConnectionFactory cf = new ConnectionFactory();
        Connection connection = cf.newConnection();
        Map<String, Object> clientProperties = connection.getClientProperties();
        System.out.println(clientProperties.toString());
        Channel channel = connection.createChannel();
        channel.queueDelete(name);
        channel.close();
        connection.close();

    }

    static void producerTest(int i) throws IOException, TimeoutException {
        //队列名称
        final String QUEUE_NAME = "queueOne" + i;
        //创建连接工厂，host默认是localhost
        ConnectionFactory cf = new ConnectionFactory();
        //创建一个连接
        Connection connection = cf.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        /*
         * 声明队列
         * 1.第一个参数表示队列名称
         * 2.第二个参数为是否持久化
         * 3.第三参数为是否是独占队列（创建者可以使用私有队列，断开后自动删除）
         * 4.第四个参数为当前所有消费者客户端连接断开时是否自动删除队列
         * 5.第五个参数为队列的其他参数
         * */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //创建一个消息
        String msg = "hello" + i;
        /*
         * 发送消息队列
         * 1.第一个参数为交换机名称
         * 2.第二个参数为队列的映射路由key
         * 3.第三个参数为消息其他属性
         * 4.第四个参数为发送消息主体
         * */
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes("utf-8"));
        System.out.println("发送消息：" + msg.getBytes("utf-8"));
        channel.close();
        connection.close();
    }

    static void customerTest(String QUEUE_NAME) throws IOException, TimeoutException {
        //队列名称
//        final String QUEUE_NAME = "queueOne"+1;
        //创建连接工厂，host默认是localhost
        ConnectionFactory cf = new ConnectionFactory();

        //创建一个连接
        Connection connection = cf.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        /*
         * 声明队列
         * 1.第一个参数表示队列名称
         * 2.第二个参数为是否持久化
         * 3.第三参数为是否是独占队列（创建者可以使用私有队列，断开后自动删除）
         * 4.第四个参数为当前所有消费者客户端连接断开时是否自动删除队列
         * 5.第五个参数为队列的其他参数
         * */
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "utf-8");
                System.out.println("接收消息：" + message);
            }
        };
        //自动回复队列应答
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);

        channel.close();
        connection.close();
    }


}
