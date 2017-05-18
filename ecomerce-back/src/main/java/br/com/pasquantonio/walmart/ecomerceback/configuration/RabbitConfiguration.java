package br.com.pasquantonio.walmart.ecomerceback.configuration;

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

    private static final String ORDER_CHECKOUT_EXCHANGE = "orderCheckout";
	public static final String BROADCAST_ECOMERCEBACK_ORDER_CHECKOUT_QUEUE = "ecomerce-back.order-checkout.queue";
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
    public Queue orderCheckoutQueue() {
        return new Queue(BROADCAST_ECOMERCEBACK_ORDER_CHECKOUT_QUEUE,durable);
    }


    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

    @Bean
    public FanoutExchange orderCheckoutExchange() {
        FanoutExchange exchange = new FanoutExchange(ORDER_CHECKOUT_EXCHANGE);
        return exchange;
    }

    @Bean
    public Binding orderCheckoutBinding() {
        return BindingBuilder.bind(orderCheckoutQueue()).to(orderCheckoutExchange());
    }
    
    @Bean
	public MappingJackson2MessageConverter jackson2Converter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}

}
