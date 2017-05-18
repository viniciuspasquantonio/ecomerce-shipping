package br.com.pasquantonio.walmart.orders.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.pasquantonio.walmart.orders.domain.Order;

public interface OrderRepository extends CrudRepository<Order, String> {

}
