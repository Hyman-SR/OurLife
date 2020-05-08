package com.ourlife.base.core.config;

import org.aspectj.lang.annotation.Around;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.ourlife.base.common.constants.MqConstants.*;

/**
 * MQ配置类
 *
 * @author zhangchao
 * @createdOn 2020/4/26
 */
@Configuration
public class RabbitMqConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    // -------------直连交换机----------------------

    @Bean
    public Queue directQueueA() {
        //durable:是否持久化，默认是false;[持久化队列，会被存储在磁盘上，当消息代理重启后仍然存在；暂存队列：当前连接有效]
        //exclusive:默认false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。次参考优先级高与durable
        //autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除
        return new Queue(DIRECT_QUEUE_A, true);
    }

    @Bean
    public DirectExchange directExchangeA() {
        return new DirectExchange(DIRECT_EXCHANGE_A, true, false);
    }

    @Bean
    public Binding bindingDirectA() {
        return BindingBuilder.bind(directQueueA()).to(directExchangeA()).with(DIRECT_TOUTING_KEY);
    }

    @Bean
    public DirectExchange lonelyDirectExchanfe() {
        return new DirectExchange("lonelyDirectExchanfe");
    }
    // -------------------------------------------
}
