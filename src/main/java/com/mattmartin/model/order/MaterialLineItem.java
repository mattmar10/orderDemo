package com.mattmartin.model.order;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a material order line item. Taxes apply.
 *
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: Matt Martin Co</p>
 * @author Matt Martin
 * @version 1.0
 */
public class MaterialLineItem implements SellableLineItem, Serializable {

    private static final long serialVersionUID = 20180215223911L;

    private final OrderItem orderItem;

    public MaterialLineItem(final OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    /**
     * Calculates the line item price for this (taxable) order line item.
     *
     * @param taxRate the tax rate to appy to this line item
     *
     * @return the line item price for this material line item.
     */
    public BigDecimal calculateLineItemPrice(float taxRate) {

        final BigDecimal itemSubTotal =
                orderItem.getItem().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));

        BigDecimal result = itemSubTotal.multiply(BigDecimal.valueOf(taxRate)).add(itemSubTotal);
        result = result.setScale(2, RoundingMode.HALF_EVEN);

        return result;
    }


    @Override
    public boolean equals(Object other){
        if(other == null || other.getClass() != getClass()){
            return false;
        }

        if(other == this){
            return true;
        }

        final MaterialLineItem otherOrderItem = (MaterialLineItem) other;
        return this.orderItem.equals(otherOrderItem.orderItem);

    }

    @Override
    public int hashCode(){
        return orderItem.hashCode();
    }


}
