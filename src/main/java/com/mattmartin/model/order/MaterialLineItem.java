package com.mattmartin.model.order;


import java.math.BigDecimal;

/**
 * Represents a material order line item. Taxes apply.
 *
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: Matt Martin Co</p>
 * @author Matt Martin
 * @version 1.0
 */
public class MaterialLineItem implements SellableLineItem {

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

        final BigDecimal itemSubTotal = orderItem.getItem().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
        return itemSubTotal.multiply(BigDecimal.valueOf(taxRate)).add(itemSubTotal);
    }


}
