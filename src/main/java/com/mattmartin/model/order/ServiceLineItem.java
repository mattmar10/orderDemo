package com.mattmartin.model.order;

import java.math.BigDecimal;

/**
 * Created by mattmartin on 2/15/18.
 */
public class ServiceLineItem implements SellableLineItem {

    private static final long serialVersionUID = 20180215223319L;

    private final OrderItem orderItem;

    public ServiceLineItem(final OrderItem orderItem){
        this.orderItem = orderItem;
    }


    /**
     * Calculates the line item price for this service line item.
     * @param taxRate not considered since business rules dictate that service line items
     *                are not taxable
     * @return the price for this order line item
     */
    public BigDecimal calculateLineItemPrice(float taxRate) {
        return orderItem.getItem().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
    }
}
