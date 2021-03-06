package com.example.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author : JCccc
 * @CreateTime : 2019/9/3
 * @Description :
 **/
 
@Component
@RabbitListener(queues = "topic.three")
public class TopicThreeReceiver {
 
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("Topic消费者收到消息  : " + testMessage.toString());
    }
}