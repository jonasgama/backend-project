package com.ninjaone.backendinterviewproject.catalog.infra.entity.entity;

import com.ninjaone.backendinterviewproject.catalog.domain.enums.CompatibilityEnum;

import javax.persistence.*;

@Entity
@Table(name="SERVICES")
public class ServiceEntity {

    @Id
    private String item;
    private Double price;
    private boolean available;
    private CompatibilityEnum compatibility;

    public ServiceEntity(){

    }

    public ServiceEntity(String item, Double price, CompatibilityEnum compatibility){
        this.item = item;
        this.price = price;
        this.compatibility = compatibility;
        this.available = true;
    }

    public CompatibilityEnum getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(CompatibilityEnum compatibility) {
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
