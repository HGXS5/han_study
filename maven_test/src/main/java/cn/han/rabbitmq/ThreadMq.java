package cn.han.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadMq {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

//        FutureTask ft = new FutureTask(new ThreadCallable());
//        new Thread(ft).start();
        for (int i = 0; i <2 ; i++) {
            new ThreadNormal().start();
        }




//        ThreadRunnable runnable = new ThreadRunnable();
//        new Thread(runnable).start();
    }

    static class ThreadNormal extends Thread {
        CustomerNormal cn = null;
        private final String Queue_name = "queue_han";
        @Override
        public void run() {

            while (true) {
                try {
                    ConnectionFactory cf = new ConnectionFactory();//创建连接工厂
                    Connection connection = cf.newConnection();//创建连接
                    Channel channel = connection.createChannel();//创建通道
                    AMQP.Queue.DeclareOk declareOk = channel.queueDeclare();
                    int messageCount = declareOk.getMessageCount();//获取队列中消息数量

                    DefaultConsumer df = new DefaultConsumer(channel) {//创建消费端对象
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                            String message = new String(body,"utf-8");
                            System.out.println(Thread.currentThread().getName()+"接收到的消息为："+message);
                        }
                    };
                    channel.basicConsume(Queue_name, true, df);//自动回复队列应答

                    System.out.println(Thread.currentThread().getName() + ":" + Queue_name);
                    Thread.sleep(1000*10);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class ThreadRunnable implements Runnable {
        CustomerNormal cn = null;

        public void run() {
            try {
                cn = new CustomerNormal();
                cn.customerTest();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + cn.getQueue_name());
        }
    }

    static class ThreadCallable implements Callable {
        CustomerNormal cn = null;

        public Object call() throws Exception {
            boolean flag = false;
            try {
                cn = new CustomerNormal();
                cn.customerTest();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + cn.getQueue_name());
            flag = true;
            return flag;
        }
    }
}
