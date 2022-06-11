package com.ninjaone.backendinterviewproject.dto;

import com.ninjaone.backendinterviewproject.enums.Compatibility;

public class DeviceDTO {

    private String item;
    private Double price;
    private boolean available;
    private Compatibility compatibility;

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