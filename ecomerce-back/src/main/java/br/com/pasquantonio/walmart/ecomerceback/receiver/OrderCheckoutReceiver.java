package br.com.pasquantonio.walmart.ecomerceback.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.ecomerceback.configuration.RabbitConfiguration;
import br.com.pasquantonio.walmart.ecomerceback.domain.ShippingOrder;
import br.com.pasquantonio.walmart.ecomerceback.service.ShippingOrderService;

@Service
public class OrderCheckoutReceiver {

	@Autowired
	private ShippingOrderService orderService;
	
	@RabbitListener(queues = RabbitConfiguration.BROADCAST_ECOMERCEBACK_ORDER_CHECKOUT_QUEUE)
	public void receiveMessage(ShippingOrder order) {
		System.out.println("Received Checkout Order<" + order + ">");
		orderService.create(order);
	}

}
