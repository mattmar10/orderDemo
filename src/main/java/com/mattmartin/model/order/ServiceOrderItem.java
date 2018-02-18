package com.mattmartin.model.order;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Represents a material order line item. Taxes do not apply.
 *
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: Matt Martin Co</p>
 * @author Matt Martin
 * @version 1.0
 */
public class ServiceOrderItem implements SellableOrderItem, Serializable{

    private static final long serialVersionUID = 20180215223319L;

    private final OrderItem orderItem;

    public ServiceOrderItem(final OrderItem orderItem){
        this.orderItem = orderItem;
    }


    /**
     * Calculates the line item price for this service line item.
     * @param taxRate not considered since business rules dictate that service line items
     *                are not taxable
     * @return the price for this order line item
     */
    public BigDecimal calculateOrderItemPrice(float taxRate) {
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

        final ServiceOrderItem otherOrderItem = (ServiceOrderItem) other;
        return this.orderItem.equals(otherOrderItem.orderItem);

    }

    @Override
    public int hashCode(){
        return orderItem.hashCode();
    }
}
