package com.mattmartin.model.order;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

/**
 * Basic building blocks of an item.
 *
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: Matt Martin Co</p>
 * @author Matt Martin
 * @version 1.0
 */
public final class Item {
    private static final long serialVersionUID = 20180215224015L;

    private final int key;
    private final String name;
    private final BigDecimal price;

    public Item(final int key, final String name, final BigDecimal price)
    {
        this.key = key;
        this.name = name;
        this.price = price;
    }

    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }


    @Override
    public boolean equals(Object other){
        if(other == null || other.getClass() != getClass()){
            return false;
        }

        if(other == this){
            return true;
        }

        final Item otherItem = (Item) other;
        return new EqualsBuilder()
                .append(key, otherItem.key)
                .append(name, otherItem.name)
                .append(price, otherItem.price).isEquals();

    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder(7, 37)
                .append(key)
                .append(name)
                .append(price).toHashCode();
    }

}
