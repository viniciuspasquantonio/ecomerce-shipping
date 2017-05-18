package br.com.pasquantonio.walmart.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.orders.domain.Order;
import br.com.pasquantonio.walmart.orders.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Iterable<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order findOne(Integer id) {
		return orderRepository.findOne(String.valueOf(id));
	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void delete(Integer id) {
		orderRepository.delete(String.valueOf(id));
		
	}

	@Override
	public void deleteAll() {
		orderRepository.deleteAll();
		
	}

}
