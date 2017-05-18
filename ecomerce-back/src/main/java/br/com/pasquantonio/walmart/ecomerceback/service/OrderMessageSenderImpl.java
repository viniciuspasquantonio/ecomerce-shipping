package br.com.pasquantonio.walmart.ecomerceback.service;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.ecomerceback.configuration.RabbitConfiguration;
import br.com.pasquantonio.walmart.ecomerceback.domain.ShippingOrder;

@Service
public class OrderMessageSenderImpl implements ShippingOrderMessageSender {

	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;

	@Autowired
	private MappingJackson2MessageConverter mappingJackson2MessageConverter;
	
	public void sendShippingOrderCreatedMessage(ShippingOrder createdOrder)  {
		this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);
		
		System.out.println("Order Availabele to Shipping "+ createdOrder);
		
		rabbitMessagingTemplate.convertAndSend(RabbitConfiguration.SHIPPING_ORDER_CREATED_EXCHANGE,"",createdOrder);
		
	}



}
