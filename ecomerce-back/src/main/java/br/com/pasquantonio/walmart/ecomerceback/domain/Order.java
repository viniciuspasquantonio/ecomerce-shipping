package br.com.pasquantonio.walmart.ecomerceback.domain;

public class Order {

	private Integer id;
	
	private Integer checkoutId;
	
	private Integer productsQuantity;
	 
	private String destinationAdress;
	
	private String invoice;
	
	private OrderState orderState;

	public Integer getProductsQuantity() {
		return productsQuantity;
	}

	public void setProductsQuantity(Integer productsQuantity) {
		this.productsQuantity = productsQuantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return this.invoice;
	}
}
