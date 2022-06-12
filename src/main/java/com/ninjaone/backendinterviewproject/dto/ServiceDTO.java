package com.ninjaone.backendinterviewproject.dto;

import com.ninjaone.backendinterviewproject.enums.Compatibility;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class ServiceDTO {

    private String item;
    private Double price;
    private boolean available;
    private Compatibility compatibility;

    public ServiceDTO(String item, Double price, boolean available, Compatibility compatibility) {
        this.item = item;
        this.price = price;
        this.available = available;
        this.compatibility = compatibility;
    }


    public String getItem() {
        return item;
    }

    public Double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public Compatibility getCompatibility() {
        return compatibility;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setCompatibility(Compatibility compatibility) {
        this.compatibility = compatibility;
    }
}
