package org.fazz.model;


import java.io.Serializable;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Hit implements Serializable {

    private Integer wordIndex;
    private Character character;

    public Hit(Integer wordIndex, Character character) {
        this.character = character;
        this.wordIndex = wordIndex;
    }

    public static Hit hit(Integer index, Character character){
        return new Hit(index, character);
    }

    public Integer getWordIndex() {
        return wordIndex;
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
