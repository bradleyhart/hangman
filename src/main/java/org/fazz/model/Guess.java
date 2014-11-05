package org.fazz.model;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Guess {
    private Character value;

    public Guess(Character value) {
        this.value = value;
    }

    public static Guess guess(Character value) {
        return new Guess(value);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }
}
