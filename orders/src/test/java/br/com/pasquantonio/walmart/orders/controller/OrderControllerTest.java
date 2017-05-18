package br.com.pasquantonio.walmart.orders.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import br.com.pasquantonio.walmart.orders.domain.Order;
import br.com.pasquantonio.walmart.orders.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

	private static final String FIRST_INVOICE = "first invoice";
	private static final String SECOND_INVOICE = "second invoice";
	private static final String THIRD_INVOICE = "third invoice";
	private static final String ID_FIELD = "id";
	private static final String FIRSTORDER_DESTINATION_ADRESS = "main street";
	private static final String SECOND_ORDER_DESTINATION_ADRESS = "vahia de abreu";
	private static final String THIRD_ORDER_DESTINATION_ADRESS = "route 66";
	private static final Integer FIRSTORDER_PRODUCTS_QUANTITY = 1;
	private static final Integer SECOND_ORDER_PRODUCTS_QUANTITY = 2;
	private static final Integer THIRD_ORDER_PRODUCTS_QUANTITY = 3;
	private static final String DESTINATION_ADRESS_FIELD = "destinationAdress";
	private static final String PRODUCTS_QUANTITY_FIELD = "productsQuantity";

	private static final String ORDERS_RESOURCE = "/orders/";
	private static final String ORDERS_RESOURCE_ID = "/orders/{id}";

	@LocalServerPort
	private int port;

	@Autowired
	private OrderService service;

	private Order firstOrder;
	private Order secondOrder;
	private Order thirdOrder;

	
	@Before
	public void setUp() {
		service.deleteAll();
		
		firstOrder = new Order();
		firstOrder.setDestinationAdress(FIRSTORDER_DESTINATION_ADRESS);
		firstOrder.setProductsQuantity(FIRSTORDER_PRODUCTS_QUANTITY);
		firstOrder.setInvoice(FIRST_INVOICE);

		firstOrder = service.save(firstOrder);
		
		secondOrder = new Order();
		secondOrder.setDestinationAdress(SECOND_ORDER_DESTINATION_ADRESS);
		secondOrder.setProductsQuantity(SECOND_ORDER_PRODUCTS_QUANTITY);
		secondOrder.setInvoice(SECOND_INVOICE);

		secondOrder = service.save(secondOrder);
		
		thirdOrder = new Order();
		thirdOrder.setDestinationAdress(THIRD_ORDER_DESTINATION_ADRESS);
		thirdOrder.setProductsQuantity(THIRD_ORDER_PRODUCTS_QUANTITY);
		thirdOrder.setInvoice(THIRD_INVOICE);
		
		RestAssured.port = port;
		
	}

	@Test
	public void getOrdersShouldReturnBothOrders() {
		given()
			.contentType("application/json")
		.when()
			.get(ORDERS_RESOURCE)
		.then()
			.statusCode(HttpStatus.SC_OK)
			.body(PRODUCTS_QUANTITY_FIELD, hasItems(FIRSTORDER_PRODUCTS_QUANTITY, SECOND_ORDER_PRODUCTS_QUANTITY))
			.body(DESTINATION_ADRESS_FIELD, hasItems(FIRSTORDER_DESTINATION_ADRESS, SECOND_ORDER_DESTINATION_ADRESS));
	}
	
	@Test
    public void canFetchFirstOrder() {
		given()
			.contentType(ContentType.JSON)
        .when()
        	.get(ORDERS_RESOURCE_ID, firstOrder.getId())
        .then()
        	.statusCode(HttpStatus.SC_OK)
        	.body(PRODUCTS_QUANTITY_FIELD, is(FIRSTORDER_PRODUCTS_QUANTITY))
    	    .body(DESTINATION_ADRESS_FIELD, is(FIRSTORDER_DESTINATION_ADRESS))
            .body(ID_FIELD, is(firstOrder.getId()));
    }
	
	@Test
	public void orderdOrderShouldReturnSavedOrder() {
	  given()
	    .body(thirdOrder)
	    .contentType(ContentType.JSON)
	  .when()
	    .post(ORDERS_RESOURCE)
	  .then()
	    .statusCode(HttpStatus.SC_CREATED)
	    .body(PRODUCTS_QUANTITY_FIELD, is(THIRD_ORDER_PRODUCTS_QUANTITY))
	    .body(DESTINATION_ADRESS_FIELD, is(THIRD_ORDER_DESTINATION_ADRESS));
	}
	
	@Test
	public void orderdOrderShouldReturnBadRequestWithoutBody() {
		given()
	    	.contentType(ContentType.JSON)
		.when()
	    	.post(ORDERS_RESOURCE)
	    .then()
	    	.statusCode(HttpStatus.SC_BAD_REQUEST);
	}
	
	@Test
	public void orderdOrderShouldReturnNotSupportedMediaTypeIfNonJSON() {
	  given()
	    .body(thirdOrder)
	  .when()
	    .post(ORDERS_RESOURCE)
	  .then()
	    .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
	}
	
	@Test
	public void updateOrderShouldReturnUpdatedOrder() {
	  // Given an unchecked first item
	  Order order = new Order();
	  order.setProductsQuantity(FIRSTORDER_PRODUCTS_QUANTITY);
	  order.setDestinationAdress(FIRSTORDER_DESTINATION_ADRESS);
	  given()
	    .body(order)
	    .contentType(ContentType.JSON)
	  .when()
	    .put(ORDERS_RESOURCE_ID, firstOrder.getId())
	  .then()
	    .statusCode(HttpStatus.SC_OK)
	    .body(PRODUCTS_QUANTITY_FIELD, is(FIRSTORDER_PRODUCTS_QUANTITY))
	    .body(DESTINATION_ADRESS_FIELD, is(FIRSTORDER_DESTINATION_ADRESS));
	}
	  
	@Test
	public void updateOrderShouldReturnBadRequestWithoutBody() {
		given()
	  		.contentType(ContentType.JSON)
		.when()
	    	.put(ORDERS_RESOURCE_ID, firstOrder.getId())
    	.then()
    		.statusCode(HttpStatus.SC_BAD_REQUEST);
	}
	  
	@Test
	public void updateOrderShouldReturnNotSupportedMediaTypeIfNonJSON() {
		given()
	    	.body(firstOrder)
    	.when()
	    	.put(ORDERS_RESOURCE_ID, firstOrder.getId())
    	.then()
	    	.statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
	}
	 
	@Test
	public void deleteItemShouldReturnNoContent() {
		given()
  			.contentType(ContentType.JSON)
		.when()
	    	.delete(ORDERS_RESOURCE_ID, secondOrder.getId())
    	.then()
	    	.statusCode(HttpStatus.SC_NO_CONTENT);
	}


}
