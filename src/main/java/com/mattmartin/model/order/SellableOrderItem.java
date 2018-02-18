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

    BigDecimal calculateOrderItemPrice(float taxRate);
}
