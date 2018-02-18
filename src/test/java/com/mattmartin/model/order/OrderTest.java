package com.mattmartin.model.order;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    void testEmptyOrder(){
        final Order order = new Order(new SellableOrderItem[]{});
        final BigDecimal bd = order.getOrderTotal(.08f);
        final BigDecimal expected =
                BigDecimal.valueOf(0.00).setScale(OrderItemFactory.DEFAULT_SCALE, OrderItemFactory.DEFAULT_ROUNDING_MODE);

        assertEquals(expected, bd);
    }

    @Test
    void testSingleMaterialItem(){
        final SellableOrderItem materialLineItem =
                OrderItemFactory.getOrderLineItem(9, "testMaterialItem", 7.99f, 3,true);

        final Order order = new Order(materialLineItem);

        BigDecimal subTotal = materialLineItem.calculateOrderItemPrice(.0825f);
        subTotal = subTotal.setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(BigDecimal.valueOf(25.95).compareTo(subTotal), 0);
        assertEquals(subTotal.compareTo(order.getOrderTotal(.0825f)), 0);
    }

    @Test
    void testSingleServiceItem(){
        final SellableOrderItem serviceLineItem =
                OrderItemFactory.getOrderLineItem(9, "testServiceItem", 7.99f, 3,false);

        final Order order = new Order(serviceLineItem);

        final BigDecimal subTotal = serviceLineItem.calculateOrderItemPrice(.0825f);

        assertEquals(BigDecimal.valueOf(23.97).compareTo(subTotal), 0);
        assertEquals(subTotal.compareTo(order.getOrderTotal(.0825f)), 0);
    }

    @Test
    void testMultipleMaterialItems(){
        final SellableOrderItem materialLineItem =
                OrderItemFactory.getOrderLineItem(1, "testMaterialItem1", 7.99f, 3,true);
        final SellableOrderItem materialLineItem2 =
                OrderItemFactory.getOrderLineItem(2, "testMaterialItem2", 1.99f, 1,true);
        final SellableOrderItem materialLineItem3 =
                OrderItemFactory.getOrderLineItem(3, "testMaterialItem3", 2.99f, 5,true);


        final Order order =
                new Order(new SellableOrderItem[] { materialLineItem, materialLineItem2, materialLineItem3});

        System.out.println(order.getOrderTotal(.0825f));

        assertEquals(BigDecimal.valueOf(44.29).compareTo(order.getOrderTotal(.0825f)), 0);

    }

    @Test
    void testMultipleServiceItems(){
        final SellableOrderItem serviceItem =
                OrderItemFactory.getOrderLineItem(1, "testServiceItem1", 7.99f, 3,false);
        final SellableOrderItem serviceItem2 =
                OrderItemFactory.getOrderLineItem(2, "testServiceItem2", 1.99f, 1,false);
        final SellableOrderItem serviceItem3 =
                OrderItemFactory.getOrderLineItem(3, "testServiceItem3", 2.99f, 5,false);


        final Order order =
                new Order(new SellableOrderItem[] { serviceItem, serviceItem2, serviceItem3});

        assertEquals(BigDecimal.valueOf(40.91).compareTo(order.getOrderTotal(.0825f)), 0);

    }


    @Test
    void testCombinedOrder(){
        final SellableOrderItem materialLineItem =
                OrderItemFactory.getOrderLineItem(1, "testMaterialItem1", 7.99f, 3,true);
        final SellableOrderItem materialLineItem2 =
                OrderItemFactory.getOrderLineItem(2, "testMaterialItem2", 1.99f, 1,true);
        final SellableOrderItem materialLineItem3 =
                OrderItemFactory.getOrderLineItem(3, "testMaterialItem3", 2.99f, 5,true);

        final SellableOrderItem serviceItem =
                OrderItemFactory.getOrderLineItem(4, "testServiceItem1", 7.99f, 3,false);
        final SellableOrderItem serviceItem2 =
                OrderItemFactory.getOrderLineItem(5, "testServiceItem2", 1.99f, 1,false);
        final SellableOrderItem serviceItem3 =
                OrderItemFactory.getOrderLineItem(6, "testServiceItem3", 2.99f, 5,false);

        final Order order =
                new Order(new SellableOrderItem[] {
                        materialLineItem,
                        materialLineItem2,
                        materialLineItem3,
                        serviceItem,
                        serviceItem2,
                        serviceItem3});

        assertEquals(BigDecimal.valueOf(85.20).compareTo(order.getOrderTotal(.0825f)), 0);

    }

    @Test
    void testOrderSerialization(){
        final SellableOrderItem materialLineItem =
                OrderItemFactory.getOrderLineItem(1, "testMaterialItem1", 7.99f, 3,true);
        final SellableOrderItem materialLineItem2 =
                OrderItemFactory.getOrderLineItem(2, "testMaterialItem2", 1.99f, 1,true);
        final SellableOrderItem materialLineItem3 =
                OrderItemFactory.getOrderLineItem(3, "testMaterialItem3", 2.99f, 5,true);

        final SellableOrderItem serviceItem =
                OrderItemFactory.getOrderLineItem(4, "testServiceItem1", 7.99f, 3,false);
        final SellableOrderItem serviceItem2 =
                OrderItemFactory.getOrderLineItem(5, "testServiceItem2", 1.99f, 1,false);
        final SellableOrderItem serviceItem3 =
                OrderItemFactory.getOrderLineItem(6, "testServiceItem3", 2.99f, 5,false);

        final Order original =
                new Order(new SellableOrderItem[] {
                        materialLineItem,
                        materialLineItem2,
                        materialLineItem3,
                        serviceItem,
                        serviceItem2,
                        serviceItem3});

        final Order copy = SerializationUtils.clone(original);
        assertEquals(original, copy);
    }

}
