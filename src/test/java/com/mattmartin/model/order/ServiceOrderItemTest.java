package com.mattmartin.model.order;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceOrderItemTest {

    @Test
    void testMateriallineItemSerialization() {
        final Item testItem = new Item(3, "testServiceLineItem", BigDecimal.valueOf(3.99));
        final OrderItem orderItem = new OrderItem(testItem, 3);
        final ServiceOrderItem original = new ServiceOrderItem(orderItem);

        final ServiceOrderItem copy = SerializationUtils.clone(original);
        assertEquals(original, copy);
    }

    @Test
    void testCalculateLineItemPrice(){
        final SellableOrderItem serviceLineItem =
                OrderItemFactory.getOrderLineItem(9, "testServiceItem", 7.99f, 3,false);

        final BigDecimal subTotal = serviceLineItem.calculateOrderItemPrice(.0825f);

        assertEquals(BigDecimal.valueOf(23.97).compareTo(subTotal), 0);
    }
}
