package br.com.pasquantonio.walmart.orders.configuration;

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
	public static final String ORDER_CHECKOUT_EXCHANGE = "orderCheckout";
	public static final String SHIPPING_ORDER_CREATED_EXCHANGE = "shippingOrderCreated";
	public static final String ORDERS_SHIPPING_ORDER_CREATED_QUEUE = "orders.shippingOrderCreated.queue";
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
    public Queue shippingOrderCreatedQueue() {
        return new Queue(ORDERS_SHIPPING_ORDER_CREATED_QUEUE,durable);
    }


    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

    @Bean
    public FanoutExchange shippingOrderCreatedExchange() {
        FanoutExchange exchange = new FanoutExchange(SHIPPING_ORDER_CREATED_EXCHANGE);
        return exchange;
    }

    @Bean
    public Binding shippingOrderCreatedBinding() {
        return BindingBuilder.bind(shippingOrderCreatedQueue()).to(shippingOrderCreatedExchange());
    }
    
    @Bean
	public MappingJackson2MessageConverter jackson2Converter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}
}
