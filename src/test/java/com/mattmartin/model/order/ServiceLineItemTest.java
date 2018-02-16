package com.mattmartin.model.order;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceLineItemTest {

    @Test
    void testMateriallineItemSerialization() {
        final Item testItem = new Item(3, "testServiceLineItem", BigDecimal.valueOf(3.99));
        final OrderItem orderItem = new OrderItem(testItem, 3);
        final ServiceLineItem original = new ServiceLineItem(orderItem);

        final ServiceLineItem copy = SerializationUtils.clone(original);
        assertEquals(original, copy);
    }
}
