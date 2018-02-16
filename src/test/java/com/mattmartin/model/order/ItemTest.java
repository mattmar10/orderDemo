package com.mattmartin.model.order;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

/**
 * Created by mattmartin on 2/15/18.
 */
public class ItemTest
{

    @Test
    void basicItemTest() {
        final Item testItem = new Item(7, "testItem", BigDecimal.valueOf(7.77));

        assertAll("testItem",
                () -> assertTrue(7 == testItem.getKey()),
                () -> assertEquals("testItem", testItem.getName()),
                () -> assertEquals(BigDecimal.valueOf(7.77), testItem.getPrice()));

    }

    @Test
    void itemEqualityTest(){
        final Item testItem = new Item(7, "testItem", BigDecimal.valueOf(7.77));
        final Item testItem2 = new Item(7, "testItem", BigDecimal.valueOf(7.77));
        final Item testItem3 = new Item(8, "testItem", BigDecimal.valueOf(7.77));

        assertEquals(testItem, testItem2);
        assertNotEquals(testItem3, testItem);
    }

}
