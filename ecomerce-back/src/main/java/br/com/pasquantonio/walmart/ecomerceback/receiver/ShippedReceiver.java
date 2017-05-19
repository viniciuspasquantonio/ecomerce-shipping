package br.com.pasquantonio.walmart.ecomerceback.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.ecomerceback.configuration.RabbitConfiguration;
import br.com.pasquantonio.walmart.ecomerceback.domain.OrderState;
import br.com.pasquantonio.walmart.ecomerceback.domain.ShippingOrder;
import br.com.pasquantonio.walmart.ecomerceback.repository.ShippingOrderRepository;
import br.com.pasquantonio.walmart.ecomerceback.service.ShippingOrderService;

@Service
public class ShippedReceiver {

	@Autowired
	private ShippingOrderRepository shippingOrderRepository;
	
	@RabbitListener(queues = RabbitConfiguration.ECOMERCEBACK_SHIPPED_QUEUE)
	public void receiveMessage(ShippingOrder order) {
		order.setOrderState(OrderState.SHIPPED);
		shippingOrderRepository.save(order);
	}

}
