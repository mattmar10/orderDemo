package com.mattmartin.model.order;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaterialLineItemTest {

    @Test
    void testMateriallineItemSerialization() {
        final Item testItem = new Item(9, "testMaterialItem", BigDecimal.valueOf(7.99));
        final OrderItem orderItem = new OrderItem(testItem, 3);
        final MaterialLineItem original = new MaterialLineItem(orderItem);

        final MaterialLineItem copy = SerializationUtils.clone(original);
        assertEquals(original, copy);
    }

    @Test
    void testCalculateLineItemPrice(){
        final SellableLineItem materialLineItem =
                OrderItemFactory.getOrderLineItem(9, "testMaterialItem", 7.99f, 3,true);

        final BigDecimal subTotal = materialLineItem.calculateLineItemPrice(.0825f);

        assertEquals(BigDecimal.valueOf(25.95).compareTo(subTotal), 0);
    }
}
