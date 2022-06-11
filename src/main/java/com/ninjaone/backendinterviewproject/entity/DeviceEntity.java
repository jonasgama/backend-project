package com.ninjaone.backendinterviewproject.entity;

import com.ninjaone.backendinterviewproject.enums.Compatibility;

import javax.persistence.*;

@Entity
@Table(name="DEVICES")
public class DeviceEntity {

    @Id
    private String item;
    private Double price;
    private boolean available;
    private Compatibility compatibility;

    public DeviceEntity(){}

    public DeviceEntity(String item,
                        Double price,
                        boolean available,
                        Compatibility compatibility){

        this.item = item;
        this.price = price;
        this.available = available;
        this.compatibility = compatibility;
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

    public Compatibility getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(Compatibility compatibility) {
        this.compatibility = compatibility;
    }
}
