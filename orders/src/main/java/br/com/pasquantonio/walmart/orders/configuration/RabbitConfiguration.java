package br.com.pasquantonio.walmart.orders.configuration;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
@EnableRabbit
public class RabbitConfiguration {
	public static final String ORDER_CHECKOUT_EXCHANGE = "orderCheckout";
	
	@Bean
	public MappingJackson2MessageConverter jackson2Converter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}
}
