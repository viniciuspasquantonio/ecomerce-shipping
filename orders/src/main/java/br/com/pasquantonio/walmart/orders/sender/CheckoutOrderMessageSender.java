package br.com.pasquantonio.walmart.orders.sender;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.orders.configuration.RabbitConfiguration;
import br.com.pasquantonio.walmart.orders.domain.CheckoutOrder;

@Service
public class CheckoutOrderMessageSender{
	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;

	@Autowired
	private MappingJackson2MessageConverter mappingJackson2MessageConverter;
	
	public void sendCheckoutMessage(CheckoutOrder order) {
		this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);
		
		System.out.println("Sending the index request through queue message");
		
		rabbitMessagingTemplate.convertAndSend(RabbitConfiguration.ORDER_CHECKOUT_EXCHANGE,"",order);
		
	}

}
