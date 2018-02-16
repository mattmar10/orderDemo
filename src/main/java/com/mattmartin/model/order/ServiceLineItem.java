package com.mattmartin.model.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by mattmartin on 2/15/18.
 */
public class ServiceLineItem implements SellableLineItem, Serializable{

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

    @Override
    public boolean equals(Object other){
        if(other == null || other.getClass() != getClass()){
            return false;
        }

        if(other == this){
            return true;
        }

        final ServiceLineItem otherOrderItem = (ServiceLineItem) other;
        return this.orderItem.equals(otherOrderItem.orderItem);

    }

    @Override
    public int hashCode(){
        return orderItem.hashCode();
    }
}
