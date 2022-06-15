package com.ninjaone.catalog.infra.entity;

import com.ninjaone.catalog.domain.enums.CatalogCompatibilityEnum;

import javax.persistence.*;

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
}
