package com.ninjaone.backendinterviewproject.catalog.entity;

import com.ninjaone.backendinterviewproject.core.enums.CompatibilityEnum;

import javax.persistence.*;

@Entity
@Table(name="DEVICES")
public class DeviceEntity {

    @Id
    private String item;
    private Double price;
    private boolean available;
    private CompatibilityEnum compatibility;

    public DeviceEntity(){}

    public DeviceEntity(String item,
                        Double price,
                        CompatibilityEnum compatibility){

        this.item = item;
        this.price = price;
        this.compatibility = compatibility;
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

    public CompatibilityEnum getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(CompatibilityEnum compatibility) {
        this.compatibility = compatibility;
    }
}
