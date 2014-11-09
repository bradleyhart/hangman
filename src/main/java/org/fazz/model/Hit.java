package org.fazz.model;


import java.io.Serializable;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Hit implements Serializable {

    private Integer index;
    private Character character;

    public Hit(Integer index, Character character) {
        this.character = character;
        this.index = index;
    }

    public static Hit hit(Integer index, Character character){
        return new Hit(index, character);
    }

    public Integer getIndex() {
        return index;
    }

    public Character getCharacter() {
        return character;
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
