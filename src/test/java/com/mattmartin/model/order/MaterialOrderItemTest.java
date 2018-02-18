package com.mattmartin.model.order;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaterialOrderItemTest {

    @Test
    void testMateriallineItemSerialization() {
        final Item testItem = new Item(9, "testMaterialItem", BigDecimal.valueOf(7.99));
        final OrderItem orderItem = new OrderItem(testItem, 3);
        final MaterialOrderItem original = new MaterialOrderItem(orderItem);

        final MaterialOrderItem copy = SerializationUtils.clone(original);
        assertEquals(original, copy);
    }

    @Test
    void testCalculateLineItemPrice(){
        final SellableOrderItem materialLineItem =
                OrderItemFactory.getOrderLineItem(9, "testMaterialItem", 7.99f, 3,true);

        BigDecimal subTotal = materialLineItem.calculateOrderItemPrice(.0825f);
        subTotal = subTotal.setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(BigDecimal.valueOf(25.95).compareTo(subTotal), 0);
    }
}
