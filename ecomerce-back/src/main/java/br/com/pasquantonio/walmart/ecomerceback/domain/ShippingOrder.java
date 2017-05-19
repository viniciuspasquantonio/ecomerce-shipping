package br.com.pasquantonio.walmart.ecomerceback.domain;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShippingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer shippingOrderId;
	
	private Integer checkoutId;
	
	private Integer deliveryId;
	
	private Integer productsQuantity;
	 
	private String destinationAdress;
	
	private String invoice;
	
	@Enumerated()
	private OrderState orderState;

	public Integer getProductsQuantity() {
		return productsQuantity;
	}

	public void setProductsQuantity(Integer productsQuantity) {
		this.productsQuantity = productsQuantity;
	}

	public Integer getShippingOrderId() {
		return shippingOrderId;
	}

	public void setShippingOrderId(Integer id) {
		this.shippingOrderId = id;
	}

	public Integer getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(Integer checkoutId) {
		this.checkoutId = checkoutId;
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

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}
	
	@Override
	public String toString() {
		return String.format("invoice: %s; destination: %s; checkoutId: %s",invoice,destinationAdress,checkoutId);
	}

	public Integer getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}
}
