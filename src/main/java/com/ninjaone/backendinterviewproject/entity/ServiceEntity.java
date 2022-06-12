package com.ninjaone.backendinterviewproject.entity;

import com.ninjaone.backendinterviewproject.enums.Compatibility;

import javax.persistence.*;

@Entity
@Table(name="SERVICES")
public class ServiceEntity {

    @Id
    private String item;
    private Double price;
    private boolean available;
    private Compatibility compatibility;

    public ServiceEntity(){

    }

    public ServiceEntity(String item, Double price, Compatibility compatibility){
        this.item = item;
        this.price = price;
        this.compatibility = compatibility;
        this.available = true;
    }

    public Compatibility getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(Compatibility compatibility) {
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
