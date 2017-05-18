package br.com.pasquantonio.walmart.ecomerceback.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.ecomerceback.configuration.RabbitConfiguration;
import br.com.pasquantonio.walmart.ecomerceback.domain.Order;

@Service
public class OrderCheckoutReceiver {

	@RabbitListener(queues = RabbitConfiguration.BROADCAST_ECOMERCEBACK_ORDER_CHECKOUT_QUEUE)
	public void receiveMessage(Order order) {
		System.out.println("Received Foo<" + order.getInvoice() + ">");
	}

}
