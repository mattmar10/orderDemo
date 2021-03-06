package com.mattmartin.model.order;

import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Singleton Factory for building order items.
 *
 * Builds {@link MaterialOrderItem} for taxable order items and {@link ServiceOrderItem} for non taxable
 * order items.
 *
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: Matt Martin Co</p>
 * @author Matt Martin
 * @version 1.0
 */
public class OrderItemFactory {

    static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    //to the nearest penny
    static final int DEFAULT_SCALE = 2;

    private OrderItemFactory(){}

    /**
     * Builds an {@link SellableOrderItem} based on line item properties. If item is taxable, builds a
     * {@link MaterialOrderItem}. If not, builds a {@link ServiceOrderItem}
     *
     * @param key
     * @param name
     * @param price
     * @param quantity
     * @param isTaxable
     *
     * @return an {@link SellableOrderItem} matching the given arguments.
     */
    public static SellableOrderItem getOrderLineItem(
            final int key,
            final String name,
            final float price,
            final int quantity,
            boolean isTaxable){

        /* Fail Fast if data is not valid. Don't allow bad data in the system */
        Validate.notNull(key, "key must not be null");
        Validate.notNull(name, "name must not be null");
        Validate.notNull(price, "price must not be null");
        Validate.isTrue(price > 0, "Price must be greater than 0");
        Validate.notNull(quantity, "quantity must not be null");
        Validate.isTrue(quantity > 0, "quanity must be greater than 0");
        Validate.notNull(isTaxable, "isTaxable must not be null");

        final BigDecimal priceBD =
                BigDecimal.valueOf(price).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);

        final OrderItem orderItem = new OrderItem(new Item(key, name, priceBD), quantity);

        /* Business requirements state that two types of line items exist, those that are taxable (Material) and those
        that are not (Service) */
        return isTaxable ? new MaterialOrderItem(orderItem) : new ServiceOrderItem(orderItem);

    }
}
