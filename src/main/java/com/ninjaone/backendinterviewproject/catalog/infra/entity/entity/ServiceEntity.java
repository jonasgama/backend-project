package com.ninjaone.backendinterviewproject.catalog.infra.entity.entity;

import com.ninjaone.backendinterviewproject.catalog.domain.enums.CatalogCompatibilityEnum;

import javax.persistence.*;

@Entity
@Table(name="SERVICES")
public class ServiceEntity {

    @Id
    private java.lang.String item;
    private Double price;
    private boolean available;
    private CatalogCompatibilityEnum compatibility;

    public ServiceEntity(){

    }

    public ServiceEntity(java.lang.String item, Double price, CatalogCompatibilityEnum compatibility){
        this.item = item;
        this.price = price;
        this.compatibility = compatibility;
        this.available = true;
    }

    public CatalogCompatibilityEnum getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(CatalogCompatibilityEnum compatibility) {
        this.compatibility = compatibility;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public java.lang.String getItem() {
        return item;
    }

    public void setItem(java.lang.String item) {
        this.item = item;
    }
}
