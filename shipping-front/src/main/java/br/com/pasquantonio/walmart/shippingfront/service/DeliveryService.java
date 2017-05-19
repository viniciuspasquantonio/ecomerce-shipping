package br.com.pasquantonio.walmart.shippingfront.service;

import br.com.pasquantonio.walmart.shippingfront.domain.Delivery;

public interface DeliveryService {

	Iterable<Delivery> findAll();

	Delivery findOne(Integer id);

	Delivery scheduleShipping(Delivery delivery);

}
