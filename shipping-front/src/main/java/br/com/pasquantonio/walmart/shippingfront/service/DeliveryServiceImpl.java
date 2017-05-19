package br.com.pasquantonio.walmart.shippingfront.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.walmart.shippingfront.domain.Delivery;
import br.com.pasquantonio.walmart.shippingfront.domain.DeliveryStatusEnum;
import br.com.pasquantonio.walmart.shippingfront.repository.DeliveryRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService{

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private ScheduleShippingMessageSender scheduleShippingMessageSender;
	
	
	@Override
	public Iterable<Delivery> findAll() {
		return deliveryRepository.findAll();
	}

	@Override
	public Delivery findOne(Integer id) {
		return deliveryRepository.findOne(String.valueOf(id));
	}

	@Override
	public Delivery scheduleShipping(Delivery delivery) {
		delivery.setStatus(DeliveryStatusEnum.REQUESTED_TO_SCHEDULE_SHIPPING);
		delivery = deliveryRepository.save(delivery);
		scheduleShippingMessageSender.requestToScheduleShippingMessage(delivery);
		return delivery;
	}

	@Override
	public Delivery create(Delivery delivery) {
		delivery.setStatus(DeliveryStatusEnum.AVAILABLE);
		return deliveryRepository.save(delivery);
	}

	@Override
	public Delivery update(Delivery delivery) {
		return deliveryRepository.save(delivery);
	}

}
