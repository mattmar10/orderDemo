package com.mattmartin.model.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Contract for a sellable order item.
 *
 *
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: Matt Martin Co</p>
 * @author Matt Martin
 * @version 1.0
 */
public interface SellableOrderItem extends Serializable {

    /**
     * Calculate the price for this order item given the taxRate.
     *
     * @param taxRate a float representing the tax rate (in decimal form)
     *
     * @return the line item price for this order item with taxes included.
     */
    BigDecimal calculateOrderItemPrice(float taxRate);
}
