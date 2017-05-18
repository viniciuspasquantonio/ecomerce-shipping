package br.com.pasquantonio.walmart.orders.controller;

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

import br.com.pasquantonio.walmart.orders.domain.CheckoutOrder;
import br.com.pasquantonio.walmart.orders.service.CheckoutOrderService;

@RestController
@RequestMapping(value="/checkoutOrders")
public class CheckoutOrderController {
	@Autowired	
	private CheckoutOrderService checkoutOrderService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<CheckoutOrder> findAll() {
		return checkoutOrderService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public CheckoutOrder findById(@PathVariable("id") Integer id) {
		return checkoutOrderService.findOne(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckoutOrder create(@RequestBody @Valid CheckoutOrder order) {
		return checkoutOrderService.save(order);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public CheckoutOrder update(@RequestBody @Valid CheckoutOrder order, @PathVariable Long id) {
		return checkoutOrderService.save(order);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		checkoutOrderService.delete(id);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {EmptyResultDataAccessException.class})
	public void handleNotFound() {}
}
