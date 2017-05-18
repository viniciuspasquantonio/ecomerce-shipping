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

import br.com.pasquantonio.walmart.orders.domain.Order;
import br.com.pasquantonio.walmart.orders.service.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderController {
	@Autowired	
	private OrderService orderService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Order> findAll() {
		return orderService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Order findById(@PathVariable("id") Integer id) {
		return orderService.findOne(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Order create(@RequestBody @Valid Order order) {
		return orderService.save(order);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Order update(@RequestBody @Valid Order order, @PathVariable Long id) {
		return orderService.save(order);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		orderService.delete(id);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {EmptyResultDataAccessException.class})
	public void handleNotFound() {}
}
