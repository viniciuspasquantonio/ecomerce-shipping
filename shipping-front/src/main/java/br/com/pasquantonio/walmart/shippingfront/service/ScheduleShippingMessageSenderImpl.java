package br.com.pasquantonio.walmart.shippingfront.service;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.shippingfront.configuration.RabbitConfiguration;
import br.com.pasquantonio.walmart.shippingfront.domain.Delivery;

@Service
public class ScheduleShippingMessageSenderImpl implements ScheduleShippingMessageSender{

	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;

	@Autowired
	private MappingJackson2MessageConverter mappingJackson2MessageConverter;
	
	public void requestToScheduleShippingMessage(Delivery delivery) {
		this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);
		rabbitMessagingTemplate.convertAndSend(RabbitConfiguration.REQUESTED_TO_SCHEDULE_SHIPPING_EXCHANGE,"",delivery);
		
	}
	
	@Override
	public void shippedMessage(Delivery delivery) {
		this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);
		rabbitMessagingTemplate.convertAndSend(RabbitConfiguration.SHIPPED_EXCHANGE,"",delivery);
		
	}
}
