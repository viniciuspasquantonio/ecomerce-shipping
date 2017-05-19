package br.com.pasquantonio.walmart.shippingfront.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pasquantonio.walmart.shippingfront.domain.Delivery;
import br.com.pasquantonio.walmart.shippingfront.service.DeliveryService;


@RestController
@RequestMapping(value="/deliveries")
public class DeliveryController {

	@Autowired	
	private DeliveryService deliveryService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Delivery> findAll() {
		return deliveryService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Delivery findById(@PathVariable("id") Integer id) {
		return deliveryService.findOne(id);
	}
	
	
	@RequestMapping(value = "/{id}/scheduleShipping", method = RequestMethod.PUT, consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Delivery scheduleShipping(@RequestBody @Valid Delivery delivery, @PathVariable Long id) {
		return deliveryService.scheduleShipping(delivery);
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {EmptyResultDataAccessException.class})
	public void handleNotFound() {}
}
