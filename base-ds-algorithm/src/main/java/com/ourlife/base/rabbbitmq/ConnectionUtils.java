package com.ourlife.base.rabbbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * MQ工具类
 *
 * @author zhangchao
 * @createdOn 2020/7/13
 */
public class ConnectionUtils {

    /**
     * 获取MQ的连接
     *
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //设置端口
        factory.setPort(5672);
        //设置vhost
        factory.setVirtualHost("base_vhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory.newConnection();
    }
}
