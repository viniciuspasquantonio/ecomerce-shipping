package br.com.pasquantonio.walmart.shippingfront.reciever;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.shippingfront.configuration.RabbitConfiguration;
import br.com.pasquantonio.walmart.shippingfront.domain.Delivery;
import br.com.pasquantonio.walmart.shippingfront.service.DeliveryService;

@Service
public class ShippingOrderCreatedReceiver {

	@Autowired
	private DeliveryService deliveryService;
	
	@RabbitListener(queues = RabbitConfiguration.SHIPPINGFRONT_CREATE_DELIVERY_QUEUE)
	public void receiveMessage(Delivery delivery) {
		deliveryService.create(delivery);
	}

}
