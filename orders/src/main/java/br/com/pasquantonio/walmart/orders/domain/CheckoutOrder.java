package br.com.pasquantonio.walmart.orders.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("checkoutOrder")
public class CheckoutOrder {

	@Id
	private Integer checkoutId;
	
	private Integer productsQuantity;
	 
	private String destinationAdress;
	
	private String invoice;
	
	private CheckoutState state;

	public Integer getProductsQuantity() {
		return productsQuantity; 
	}

	public void setProductsQuantity(Integer productsQuantity) {
		this.productsQuantity = productsQuantity;
	}

	public String getDestinationAdress() {
		return destinationAdress;
	}

	public void setDestinationAdress(String destinationAdress) {
		this.destinationAdress = destinationAdress;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public Integer getCheckoutId() {
		return checkoutId;
	}

	public void setId(Integer id) {
		this.checkoutId = id;
	}
	public CheckoutState getState() {
		return state;
	}

	public void setState(CheckoutState state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return String.format("checkout accepted! invoice: %s; destination: %s; checkoutId: %s",invoice,destinationAdress,checkoutId);
	}

}
