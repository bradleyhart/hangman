package org.fazz.model;

import static org.apache.commons.lang3.builder.EqualsBuilder.*;
import static org.apache.commons.lang3.builder.HashCodeBuilder.*;

public class Hangman {

    private String id;
    private String make;
    private String model;
    private Integer year;
    private Integer price;

    private Hangman() {
        // For Spring
    }

    private Hangman(String make, String model, Integer year, Integer price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public static Hangman hangman(String make, String model, Integer year, Integer price) {
        return new Hangman(make, model, year, price);
    }

    public String getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
