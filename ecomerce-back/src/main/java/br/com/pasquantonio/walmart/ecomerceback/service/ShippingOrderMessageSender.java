package br.com.pasquantonio.walmart.ecomerceback.service;

import br.com.pasquantonio.walmart.ecomerceback.domain.ShippingOrder;

public interface ShippingOrderMessageSender {

	void sendShippingOrderCreatedMessage(ShippingOrder createdOrder);

	void sendShippingOrderScheduledMessage(ShippingOrder order);

}
