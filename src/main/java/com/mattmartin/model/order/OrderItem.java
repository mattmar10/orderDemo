package com.mattmartin.model.order;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Created by mattmartin on 2/15/18.
 */
public final class OrderItem implements Serializable
{
    private static final long serialVersionUID = 20180215223010L;

    private final Item item;
    private final int quantity;

    /**
     * Package private constructor. Callers should use {@link OrderItemFactory for initialization}.
     *
     * @param item
     * @param quantity
     */
    OrderItem(final Item item, final int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object other){
        if(other == null || other.getClass() != getClass()){
            return false;
        }

        if(other == this){
            return true;
        }

        final OrderItem otherOrderItem = (OrderItem) other;
        return new EqualsBuilder()
                .append(item, otherOrderItem.item)
                .append(quantity, otherOrderItem.quantity).isEquals();

    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder(9, 17)
                .append(item)
                .append(quantity).toHashCode();
    }
}
