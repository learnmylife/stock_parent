package com.itsun.stock.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
    /**
     * json格式序列化
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 定义交换机
     * @return durable: 持久化
     */
    @Bean
    public TopicExchange stockTopicEXchange(){
        return new TopicExchange("stockExchange",true,false);
    }
    @Bean
    public Queue innerMarketQueue(){
        return new Queue("innerMarketQueue",true);
    }
    /**
     * 绑定队列到指定交换机
     * @return
     */
    @Bean
    public Binding bindingInnerMarketExchange(){
        return BindingBuilder.bind(innerMarketQueue()).to(stockTopicEXchange())
                .with("inner.market");
    }
}
