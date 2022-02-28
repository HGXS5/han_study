package cn.han.rabbitmq.rabbitmqalls;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 正常工作模式
 * 不声明交换机名称，使用默认交换机
 */
public class RabbitMqWorkQueue {
    private final static String WORK_QUEUE = "work_queue";
    //获取channel
    Channel channel = null;
    //生产者
    public void producerSendMessage() {
        try {
            channel = RabbitMqConnect.getConnect();
            if (channel!=null){
                //声明队列
                /**
                 * params1:队列名称
                 * params2:是否持久化
                 * params3:队列是否独占此连接
                 * params4:队列不再使用时是否自动删除此队列
                 * params5:队列参数
                 */
                channel.queueDeclare(WORK_QUEUE, false, false, false, null);
                for (int i = 0; i <6 ; i++) {//连续生成6条信息
                    //创建消息
                    String message = "han" + System.currentTimeMillis();
                    //消息发布
                    /**
                     * params1：Exchange(交换机)名称，没有指定则使用Default Exchange
                     * params2:routingKey(队列名称),消息的路由key,是用于Exchange将消息转发到指定消息队列
                     * params3:消息包含属性
                     * params4：消息体
                     */
                    channel.basicPublish("",WORK_QUEUE,null,message.getBytes("utf-8"));
                    System.out.println("send message："+message);
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

    //消费者
    @Test
   public void customerAcceptMessage(){
       try {
           channel = RabbitMqConnect.getConnect();
           if (channel!=null){
               //声明队列
               channel.queueDeclare(WORK_QUEUE, false, false, false, null);
               //定义消费方法
               DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
                   @Override
                   public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                       //获取交换机名称
                       String exchange = envelope.getExchange();
                       System.out.println("获取交换机名称:"+exchange);
                       //获取路由key
                       String routingKey = envelope.getRoutingKey();
                       System.out.println("获取路由key:"+routingKey);
                       //消息id
                       long deliveryTag = envelope.getDeliveryTag();
                       System.out.println("消息id:"+deliveryTag);
                       //消息内容
                       String msg = new String(body, "utf-8");
                       System.out.println("消息内容:"+msg);
                       try {
                           Thread.sleep(3000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       System.out.println("----------------分割线--------------");
                   }
               };

               //监听消息队列
               /**
                * params1:队列名称
                * params2：是否自动回复，
                *       设置为true表示消息接收到并自动向mq回复接受到了，mq接受到回复会自动删除消息；
                *       设置false时，需要手动回复；
                * params3:消费消息的方法，消费者接收到消息后调用此方法
                */
               while (true){
                   channel.basicConsume(WORK_QUEUE, true, defaultConsumer);
               }
           }

       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
