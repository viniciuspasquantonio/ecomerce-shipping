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
		delivery.setStatus(DeliveryStatusEnum.SHIPPING_SCHEDULE_REQUESTED);
		return deliveryRepository.save(delivery);
	}

}
