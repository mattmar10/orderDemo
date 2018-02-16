package com.mattmartin.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


public class OrderItemTest {

    @Test
    void basicOrderItemTest(){
        final Item testItem = new Item(7, "testItem", BigDecimal.valueOf(7.77));
        final OrderItem orderItem = new OrderItem(testItem, 3);

        assertEquals(testItem, orderItem.getItem());
        assertEquals(3, orderItem.getQuantity());
    }

    @Test
    void testOrderItemEquality() {
        final Item testItem = new Item(7, "testItem", BigDecimal.valueOf(7.77));
        final OrderItem orderItem = new OrderItem(testItem, 3);
        final Item testItem2 = new Item(7, "testItem", BigDecimal.valueOf(7.77));
        final OrderItem orderItem2 = new OrderItem(testItem2, 3);

        assertEquals(orderItem, orderItem2);

        final Item testItem3 = new Item(7, "testItem", BigDecimal.valueOf(7.79));
        assertNotEquals(testItem, testItem3);

    }

    @Test
    void testOrderItemSerialization() {
        final Item testItem = new Item(7, "testItem", BigDecimal.valueOf(7.77));
        final OrderItem original = new OrderItem(testItem, 3);

        final OrderItem copy = SerializationUtils.clone(original);
        assertEquals(original, copy);
    }
}
