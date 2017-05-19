package br.com.pasquantonio.walmart.ecomerceback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.ecomerceback.domain.ShippingOrder;
import br.com.pasquantonio.walmart.ecomerceback.domain.OrderState;
import br.com.pasquantonio.walmart.ecomerceback.repository.ShippingOrderRepository;

@Service
public class ShippingOrderServiceImpl implements ShippingOrderService{

	@Autowired
	private ShippingOrderRepository orderRepository;
	
	@Autowired
	private ShippingOrderMessageSender orderMessageSender;
	
	@Override
	public ShippingOrder create(ShippingOrder order) {
		order.setOrderState(OrderState.AVAILABE);
		ShippingOrder createdOrder =  orderRepository.save(order);
		orderMessageSender.sendShippingOrderCreatedMessage(createdOrder);
		return createdOrder;
	}

	@Override
	public void schedule(ShippingOrder order) {
		order.setOrderState(OrderState.SHIPPING_SCHEDULED);
	    order =  orderRepository.save(order);
		orderMessageSender.sendShippingOrderScheduledMessage(order);
		
	}

}
