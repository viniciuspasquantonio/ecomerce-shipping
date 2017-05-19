package br.com.pasquantonio.walmart.ecomerceback.service;

import br.com.pasquantonio.walmart.ecomerceback.domain.ShippingOrder;

public interface ShippingOrderService {

	ShippingOrder create(ShippingOrder order);

	void schedule(ShippingOrder order);

	
}
