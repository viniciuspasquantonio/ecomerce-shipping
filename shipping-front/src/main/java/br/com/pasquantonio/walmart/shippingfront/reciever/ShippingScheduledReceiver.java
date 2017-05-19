package br.com.pasquantonio.walmart.shippingfront.reciever;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.shippingfront.configuration.RabbitConfiguration;
import br.com.pasquantonio.walmart.shippingfront.domain.Delivery;
import br.com.pasquantonio.walmart.shippingfront.domain.DeliveryStatusEnum;
import br.com.pasquantonio.walmart.shippingfront.service.DeliveryService;

@Service
public class ShippingScheduledReceiver {

	@Autowired
	private DeliveryService deliveryService;
	
	@RabbitListener(queues = RabbitConfiguration.SHIPPINGFRONT_SHIPPING_SCHEDULED_QUEUE)
	public void receiveMessage(Delivery delivery) {
		delivery.setStatus(DeliveryStatusEnum.SHIPPING_SCHEDULED);
		deliveryService.update(delivery);
	}


}
