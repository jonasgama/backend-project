package com.ninjaone.catalog.infra.entity;

import com.ninjaone.catalog.domain.enums.CatalogCompatibilityEnum;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public class CatalogEntity {

    @Id
    private String item;
    private Double price;
    private boolean available;
    private CatalogCompatibilityEnum compatibility;

    public CatalogEntity(){}

    public CatalogEntity(String item,
                         Double price,
                         String compatibility){

        this.item = item;
        this.price = price;
        this.compatibility = CatalogCompatibilityEnum.valueOf(compatibility.toUpperCase());
        this.available = true;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public CatalogCompatibilityEnum getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(CatalogCompatibilityEnum compatibility) {
        this.compatibility = compatibility;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogEntity that = (CatalogEntity) o;
        return available == that.available && Objects.equals(item, that.item) && Objects.equals(price, that.price) && compatibility == that.compatibility;
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, price, available, compatibility);
    }

    @Override
    public String toString() {
        return "CatalogEntity{" +
                "item='" + item + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", compatibility=" + compatibility +
                '}';
    }
}
