package com.ninjaone.backendinterviewproject.catalog.domain.dto;

import com.ninjaone.backendinterviewproject.catalog.domain.enums.CompatibilityEnum;

public class ServiceDTO {

    private String item;
    private Double price;
    private boolean available;
    private CompatibilityEnum compatibility;

    public ServiceDTO(String item, Double price, boolean available, CompatibilityEnum compatibility) {
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

    public CompatibilityEnum getCompatibility() {
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

    public void setCompatibility(CompatibilityEnum compatibility) {
        this.compatibility = compatibility;
    }
}
