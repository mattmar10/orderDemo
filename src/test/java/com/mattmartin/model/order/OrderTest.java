package com.mattmartin.model.order;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    void testSingleMaterialItem(){
        final SellableLineItem materialLineItem =
                OrderItemFactory.getOrderLineItem(9, "testMaterialItem", 7.99f, 3,true);

        final Order order = new Order(materialLineItem);

        BigDecimal subTotal = materialLineItem.calculateLineItemPrice(.0825f);
        subTotal = subTotal.setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(BigDecimal.valueOf(25.95).compareTo(subTotal), 0);
        assertEquals(subTotal.compareTo(order.getOrderTotal(.0825f)), 0);
    }

    @Test
    void testSingleServiceItem(){
        final SellableLineItem serviceLineItem =
                OrderItemFactory.getOrderLineItem(9, "testServiceItem", 7.99f, 3,false);

        final Order order = new Order(serviceLineItem);

        final BigDecimal subTotal = serviceLineItem.calculateLineItemPrice(.0825f);

        assertEquals(BigDecimal.valueOf(23.97).compareTo(subTotal), 0);
        assertEquals(subTotal.compareTo(order.getOrderTotal(.0825f)), 0);
    }

    @Test
    void testMultipleMaterialItems(){
        final SellableLineItem materialLineItem =
                OrderItemFactory.getOrderLineItem(1, "testMaterialItem1", 7.99f, 3,true);
        final SellableLineItem materialLineItem2 =
                OrderItemFactory.getOrderLineItem(2, "testMaterialItem2", 1.99f, 1,true);
        final SellableLineItem materialLineItem3 =
                OrderItemFactory.getOrderLineItem(3, "testMaterialItem3", 2.99f, 5,true);


        final Order order =
                new Order(new SellableLineItem[] { materialLineItem, materialLineItem2, materialLineItem3});

        System.out.println(order.getOrderTotal(.0825f));

        assertEquals(BigDecimal.valueOf(44.29).compareTo(order.getOrderTotal(.0825f)), 0);

    }

    @Test
    void testMultipleServiceItems(){
        final SellableLineItem serviceItem =
                OrderItemFactory.getOrderLineItem(1, "testServiceItem1", 7.99f, 3,false);
        final SellableLineItem serviceItem2 =
                OrderItemFactory.getOrderLineItem(2, "testServiceItem2", 1.99f, 1,false);
        final SellableLineItem serviceItem3 =
                OrderItemFactory.getOrderLineItem(3, "testServiceItem3", 2.99f, 5,false);


        final Order order =
                new Order(new SellableLineItem[] { serviceItem, serviceItem2, serviceItem3});

        assertEquals(BigDecimal.valueOf(40.91).compareTo(order.getOrderTotal(.0825f)), 0);

    }


    @Test
    void testCombinedOrder(){
        final SellableLineItem materialLineItem =
                OrderItemFactory.getOrderLineItem(1, "testMaterialItem1", 7.99f, 3,true);
        final SellableLineItem materialLineItem2 =
                OrderItemFactory.getOrderLineItem(2, "testMaterialItem2", 1.99f, 1,true);
        final SellableLineItem materialLineItem3 =
                OrderItemFactory.getOrderLineItem(3, "testMaterialItem3", 2.99f, 5,true);

        final SellableLineItem serviceItem =
                OrderItemFactory.getOrderLineItem(4, "testServiceItem1", 7.99f, 3,false);
        final SellableLineItem serviceItem2 =
                OrderItemFactory.getOrderLineItem(5, "testServiceItem2", 1.99f, 1,false);
        final SellableLineItem serviceItem3 =
                OrderItemFactory.getOrderLineItem(6, "testServiceItem3", 2.99f, 5,false);

        final Order order =
                new Order(new SellableLineItem[] {
                        materialLineItem,
                        materialLineItem2,
                        materialLineItem3,
                        serviceItem,
                        serviceItem2,
                        serviceItem3});

        assertEquals(BigDecimal.valueOf(85.20).compareTo(order.getOrderTotal(.0825f)), 0);

    }

}
