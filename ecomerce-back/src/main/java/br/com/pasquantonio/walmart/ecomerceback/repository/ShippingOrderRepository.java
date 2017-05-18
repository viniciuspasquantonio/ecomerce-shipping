package br.com.pasquantonio.walmart.ecomerceback.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pasquantonio.walmart.ecomerceback.domain.ShippingOrder;

@Repository
public interface ShippingOrderRepository extends CrudRepository<ShippingOrder, Integer> {

}
