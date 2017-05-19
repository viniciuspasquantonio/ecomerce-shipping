package br.com.pasquantonio.walmart.shippingfront.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.pasquantonio.walmart.shippingfront.domain.Delivery;

public interface DeliveryRepository extends CrudRepository<Delivery, String>{

}
