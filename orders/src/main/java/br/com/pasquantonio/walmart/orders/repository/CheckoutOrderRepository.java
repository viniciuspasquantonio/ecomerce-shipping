package br.com.pasquantonio.walmart.orders.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.pasquantonio.walmart.orders.domain.CheckoutOrder;

public interface CheckoutOrderRepository extends CrudRepository<CheckoutOrder, String> {

}
