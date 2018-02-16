package com.mattmartin.model.order;

import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;

/**
 * Singleton Factory for building order items.
 *
 * Builds {@link MaterialLineItem} for taxable order items and {@link ServiceLineItem} for non taxable
 * order items.
 *
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: Matt Martin Co</p>
 * @author Matt Martin
 * @version 1.0
 */
public class OrderItemFactory {

    private static final OrderItemFactory instance = new OrderItemFactory();

    private OrderItemFactory(){}

    public static OrderItemFactory getInstance(){
        return instance;
    }

    /**
     * Builds an {@link SellableLineItem} based on line item properties. If item is taxable, builds a
     * {@link MaterialLineItem}. If not, builds a {@link ServiceLineItem}
     *
     * @param key
     * @param name
     * @param price
     * @param quantity
     * @param isTaxable
     *
     * @return an {@link SellableLineItem} matching the given arguments.
     */
    public static SellableLineItem getOrderLineItem(
            final int key,
            final String name,
            final BigDecimal price,
            final int quantity,
            boolean isTaxable){

        /* Fail Fast if data is not valid. Don't allow bad data in the system */
        Validate.notNull(key, "key must not be null");
        Validate.notNull(name, "name must not be null");
        Validate.notNull(price, "price must not be null");
        Validate.isTrue(price.compareTo(BigDecimal.ZERO) > 0, "Price must be greater than 0");
        Validate.notNull(quantity, "quantity must not be null");
        Validate.isTrue(quantity > 0, "quanity must be greater than 0");
        Validate.notNull(isTaxable, "isTaxable must not be null");

        final OrderItem orderItem = new OrderItem(new Item(key, name, price), quantity);

        /* Business requirements state that two types of line items exist, those that are taxable (Material) and those
        that are not (Service) */
        return isTaxable ? new MaterialLineItem(orderItem) : new ServiceLineItem(orderItem);

    }
}
