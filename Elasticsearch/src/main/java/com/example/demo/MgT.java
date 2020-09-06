package com.example.demo;

import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MgT {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setVirtualHost("mqtest");
        connectionFactory.setUsername("mq");
        connectionFactory.setPassword("123");
        Connection connection = connectionFactory.newConnection();
//        创建消息队列

        Channel channel = connection.createChannel();
//        绑定消息队列
        /**
         * durable 持久化
         * exclusive 独占队列
         * autoDelete 自动删除
         * queueDeclare（String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments）
         */
        channel.queueDeclare("lululua",false,false,false,null);
//        发布消息，交换机
        /**
         * basicConsume(String queue, boolean autoAck, DeliverCallback deliverCallback, CancelCallback cancelCallback)
         */
        channel.basicConsume("lululua", true, new DeliverCallback() {
            @SneakyThrows
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(message);
                Thread.sleep(1000);
            }
        }, consumerTag -> { });

        connection.close();
    }
}
