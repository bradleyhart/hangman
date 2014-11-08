package org.fazz.model;

import java.io.Serializable;
import java.util.HashSet;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Word implements Serializable {
    private static final HashSet<String> EXCLUDE_FIELDS = new HashSet<String>() {{
        add("random");
    }};

    private String value;
    private transient Double random;

    public Word(String value) {
        this.value = value;
        this.random = Math.random();
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Word word(String word){
        return new Word(word);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this, EXCLUDE_FIELDS);
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj, EXCLUDE_FIELDS);
    }
}
