package br.com.pasquantonio.walmart.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.orders.domain.CheckoutOrder;
import br.com.pasquantonio.walmart.orders.domain.CheckoutState;
import br.com.pasquantonio.walmart.orders.repository.CheckoutOrderRepository;
import br.com.pasquantonio.walmart.orders.sender.CheckoutOrderMessageSender;

@Service
public class CheckoutOrderServiceImpl implements CheckoutOrderService {

	@Autowired
	private CheckoutOrderRepository orderRepository;
	
	@Autowired
	private CheckoutOrderMessageSender checkoutOrderMessageSender;
	

	@Override
	public Iterable<CheckoutOrder> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public CheckoutOrder findOne(Integer id) {
		return orderRepository.findOne(String.valueOf(id));
	}

	@Override
	public CheckoutOrder create(CheckoutOrder order) {
		order.setState(CheckoutState.PENDING);
		CheckoutOrder createdOrder =  orderRepository.save(order);
		checkoutOrderMessageSender.sendCheckoutMessage(createdOrder);
		return createdOrder;
	}

	@Override
	public void delete(Integer id) {
		orderRepository.delete(String.valueOf(id));

	}

	@Override
	public void deleteAll() {
		orderRepository.deleteAll();

	}

	@Override
	public CheckoutOrder update(CheckoutOrder order) {
		return orderRepository.save(order);
	}


}

