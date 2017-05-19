package br.com.pasquantonio.walmart.shippingfront.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
@EnableRabbit
public class RabbitConfiguration {
	public static final String REQUESTED_TO_SCHEDULE_SHIPPING_EXCHANGE = "requestedToScheduleShipping";
	public static final String SHIPPING_SCHEDULED_EXCHANGE = "shippingScheduled";
	public static final String SHIPPING_ORDER_CREATED_EXCHANGE = "shippingOrderCreated";
	public static final String SHIPPINGFRONT_CREATE_DELIVERY_QUEUE = "shippingfront.createDelivery.queue";
	public static final String SHIPPINGFRONT_SHIPPING_SCHEDULED_QUEUE = "shippingfront.shippingScheduled.queue";
	private static final boolean durable = true;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }


    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }



    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

    @Bean
    public Queue createDeliveryQueue() {
    	return new Queue(SHIPPINGFRONT_CREATE_DELIVERY_QUEUE,durable);
    }

    @Bean
    public FanoutExchange shippingOrderCreatedExchange() {
        FanoutExchange exchange = new FanoutExchange(SHIPPING_ORDER_CREATED_EXCHANGE);
        return exchange;
    }

    @Bean
    public Binding shippingOrderCreatedBinding() {
        return BindingBuilder.bind(createDeliveryQueue()).to(shippingOrderCreatedExchange());
    }
    
    
    @Bean
    public Queue shippingScheduledQueue() {
    	return new Queue(SHIPPINGFRONT_SHIPPING_SCHEDULED_QUEUE,durable);
    }

    @Bean
    public FanoutExchange shippingScheduledExchange() {
        FanoutExchange exchange = new FanoutExchange(SHIPPING_SCHEDULED_EXCHANGE);
        return exchange;
    }

    @Bean
    public Binding shippingScheduledExchangeBinding() {
        return BindingBuilder.bind(shippingScheduledQueue()).to(shippingScheduledExchange());
    }
    
    @Bean
	public MappingJackson2MessageConverter jackson2Converter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}
}
