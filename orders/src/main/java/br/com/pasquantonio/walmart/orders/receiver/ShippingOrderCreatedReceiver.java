package br.com.pasquantonio.walmart.orders.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.orders.configuration.RabbitConfiguration;
import br.com.pasquantonio.walmart.orders.domain.CheckoutOrder;
import br.com.pasquantonio.walmart.orders.domain.CheckoutState;
import br.com.pasquantonio.walmart.orders.service.CheckoutOrderService;

@Service
public class ShippingOrderCreatedReceiver {

	@Autowired
	private CheckoutOrderService checkoutOrderService;
	
	@RabbitListener(queues = RabbitConfiguration.ORDERS_SHIPPING_ORDER_CREATED_QUEUE)
	public void receiveMessage(CheckoutOrder order) {
		System.out.println("Received Shipping Order Created<" + order + ">");
		order.setState(CheckoutState.ACCEPTED);
		checkoutOrderService.update(order);
	}

}
