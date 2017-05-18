package br.com.pasquantonio.walmart.orders.service;

import br.com.pasquantonio.walmart.orders.domain.CheckoutOrder;

public interface CheckoutOrderService {

	Iterable<CheckoutOrder> findAll();

	CheckoutOrder findOne(Integer id);

	CheckoutOrder create(CheckoutOrder order);

	void delete(Integer id);

	void deleteAll();
	
	CheckoutOrder update(CheckoutOrder order);


}
