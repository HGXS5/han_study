package com.han.rabbitmq.component;

import com.han.rabbitmq.config.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRabbitMq {
    //监听邮箱队列
    @RabbitListener(queues = {RabbitMqConfig.QUEUE_INFORM_EMAIL})
    public void receive_email(String msg, Message message, Channel channel){
        System.out.println("email:"+msg);
        System.out.println("email:"+message);
        System.out.println("email:"+channel);
    }
    //监听sms队列
    @RabbitListener(queues = {RabbitMqConfig.QUEUE_INFORM_SMS})
    public void receive_sms(String msg,Message message,Channel channel){
        System.out.println(msg);
        System.out.println(message);
        System.out.println(channel);
    }
}
