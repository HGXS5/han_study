package cn.han.rabbitmq.rabbitmqalls;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

public class RabbitMqConnect {

    private final static String USERNAME = "han";
    private final static String PASSWORD = "Han#222";
    private final static String HOST = "47.100.77.11";
    private final static Integer PORT = 5672;
    private static Connection connection = null;
    private static Channel channel = null;

    private RabbitMqConnect() {
    }

    public static Channel getConnect() throws Exception {
        return getRabbitMQChannel();
    }

    private static Channel getRabbitMQChannel() throws Exception {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setUsername(USERNAME);
        cf.setPassword(PASSWORD);
        cf.setHost(HOST);
        cf.setPort(PORT);
        connection = cf.newConnection();
        channel = connection.createChannel();
        return channel;
    }

    public static boolean close() throws Exception {
        boolean closeConnect = false;//是否全部关闭标识
        if (connection!=null&&channel!=null){
            channel.close();
            connection.close();
            closeConnect = true;
            return closeConnect;
        }else {
            if (channel!=null){
                channel.close();
            }
            if (connection!=null){
                connection.close();
            }
            return closeConnect;
        }
    }
}
