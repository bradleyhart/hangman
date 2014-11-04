package org.fazz.query;

import org.springframework.data.mongodb.core.query.Criteria;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class LetterQuery {

    private String make;
    private String model;
    private Integer year;
    private Integer price;

    public static LetterQuery carSearch() {
        return new LetterQuery();
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

    public Criteria toMongoCriteria() {
        Criteria criteria = new Criteria();
        if (isNotBlank(make)) {
            criteria.and("make").is(make);
        }
        if (isNotBlank(model)) {
            criteria.and("model").is(model);
        }
        if (price != null) {
            criteria.and("price").is(price);
        }
        if (year != null) {
            criteria.and("year").is(year);
        }
        return criteria;
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
