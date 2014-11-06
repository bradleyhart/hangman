package org.fazz.model;


import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Hit {

    private Integer index;
    private Character character;

    public Hit(Integer index, Character character) {
        this.character = character;
        this.index = index;
    }

    public static Hit hit(Integer index, Character character){
        return new Hit(index, character);
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