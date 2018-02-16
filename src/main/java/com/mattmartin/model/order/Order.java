package com.mattmartin.model.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Represents and Order that contains a collection of items.
 *
 * care should be taken to ensure that this class is immutable since it
 * is sent to other systems for processing that should not be able
 * to change it in any way.
 *
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: Matt Martin Co</p>
 * @author Matt Martin
 * @version 1.0
 */

public class Order implements Serializable{
    private static final long serialVersionUID = 20180215222913L;

    private final SellableLineItem[] orderItems;

    public Order(final SellableLineItem... orderItems) {
        this. orderItems = orderItems;
    }

    public float getOrderTotal(final float taxRate)
    {
        final BigDecimal result = Arrays.stream(orderItems)
                .map(oItem -> oItem.calculateLineItemPrice(taxRate))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return result.floatValue();
    }

    public SellableLineItem[] getORderItems(){
        return orderItems;
    }

}
