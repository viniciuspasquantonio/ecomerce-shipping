package br.com.pasquantonio.walmart.ecomerceback.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.pasquantonio.walmart.ecomerceback.domain.ShippingOrder;
import br.com.pasquantonio.walmart.ecomerceback.domain.OrderState;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ShippingOrderRepositoryTest {

	private static final String ORDER_DESTINATION_ADRESS = "Sesamo street";
	private static final Integer ORDER_PRODUCTS_QUANTITY = 2;
	private static final String ORDER_INVOICE = "1234";
	private static final OrderState ORDER_STATE = OrderState.AVAILABE;

    @Autowired
    private ShippingOrderRepository orderRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        ShippingOrder order= new ShippingOrder();
        order.setDestinationAdress(ORDER_DESTINATION_ADRESS);
        order.setProductsQuantity(ORDER_PRODUCTS_QUANTITY);
        order.setInvoice(ORDER_INVOICE);
        order.setOrderState(ORDER_STATE);

        //when
        orderRepository.save(order);

        //then
        Assert.assertNotNull(order.getId());
        ShippingOrder newOrder = orderRepository.findOne(order.getId());
        Assert.assertEquals(ORDER_DESTINATION_ADRESS, newOrder.getDestinationAdress());
        Assert.assertEquals(ORDER_PRODUCTS_QUANTITY.compareTo(newOrder.getProductsQuantity()), 0);
        Assert.assertEquals(ORDER_INVOICE, newOrder.getInvoice());
        Assert.assertEquals(ORDER_STATE, newOrder.getOrderState());
    }
}
