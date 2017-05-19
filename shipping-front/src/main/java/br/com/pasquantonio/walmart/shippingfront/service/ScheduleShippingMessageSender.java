package br.com.pasquantonio.walmart.shippingfront.service;

import br.com.pasquantonio.walmart.shippingfront.domain.Delivery;

public interface ScheduleShippingMessageSender {
	void requestToScheduleShippingMessage(Delivery delivery);

	void shippedMessage(Delivery delivery);
}
