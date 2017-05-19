package br.com.pasquantonio.walmart.shippingfront.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.pasquantonio.walmart.shippingfront.domain.Delivery;
import br.com.pasquantonio.walmart.shippingfront.domain.DeliveryStatusEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DeliveryRepositoryTest {

	private static final String ORDER_DESTINATION_ADRESS = "Sesamo street";
	private static final Integer ORDER_PRODUCTS_QUANTITY = 2;
	private static final String ORDER_INVOICE = "1234";
	private static final String CARRIER_NAME = "Transport";
	private static final Integer SHIPPING_ID = 2;
	private static final DeliveryStatusEnum DELIVERY_STATUS = DeliveryStatusEnum.AVAILABLE;

    @Autowired
    private DeliveryRepository orderRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Delivery delivery= new Delivery();
        delivery.setDestinationAdress(ORDER_DESTINATION_ADRESS);
        delivery.setProductsQuantity(ORDER_PRODUCTS_QUANTITY);
        delivery.setInvoice(ORDER_INVOICE);
        delivery.setCarrierName(CARRIER_NAME);
        delivery.setShippingOrderId(SHIPPING_ID);
        delivery.setStatus(DELIVERY_STATUS);

        //when
        orderRepository.save(delivery);

        //then
        Assert.assertNotNull(delivery.getId());
        Delivery newOrder = orderRepository.findOne(String.valueOf(delivery.getId()));
        Assert.assertEquals(ORDER_DESTINATION_ADRESS, newOrder.getDestinationAdress());
        Assert.assertEquals(ORDER_PRODUCTS_QUANTITY.compareTo(newOrder.getProductsQuantity()), 0);
        Assert.assertEquals(ORDER_INVOICE, newOrder.getInvoice());
        Assert.assertEquals(CARRIER_NAME, newOrder.getCarrierName());
        Assert.assertEquals(SHIPPING_ID, newOrder.getShippingOrderId());
        Assert.assertEquals(DELIVERY_STATUS, newOrder.getStatus());
    }
}
