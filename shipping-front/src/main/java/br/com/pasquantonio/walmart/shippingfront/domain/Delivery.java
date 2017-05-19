package br.com.pasquantonio.walmart.shippingfront.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("delivery")
public class Delivery {

	@Id
	private Integer id;

	private Integer shippingOrderId;
	private Integer productsQuantity;

	private String destinationAdress;

	private String invoice;
	
	private String carrierName;
	
	private DeliveryStatusEnum status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public DeliveryStatusEnum getStatus() {
		return status;
	}

	public void setStatus(DeliveryStatusEnum status) {
		this.status = status;
	}

	public Integer getShippingOrderId() {
		return shippingOrderId;
	}

	public void setShippingOrderId(Integer shippingOrderId) {
		this.shippingOrderId = shippingOrderId;
	}
	
}
