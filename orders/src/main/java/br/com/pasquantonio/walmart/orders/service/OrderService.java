package br.com.pasquantonio.walmart.orders.service;

import br.com.pasquantonio.walmart.orders.domain.Order;

public interface OrderService {

	Iterable<Order> findAll();

	Order findOne(Integer id);

	Order save(Order order);

	void delete(Integer id);

	void deleteAll();

}
