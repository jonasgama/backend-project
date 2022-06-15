package com.ninjaone.backendinterviewproject.catalog.domain.dto;

import com.ninjaone.backendinterviewproject.catalog.domain.enums.CatalogCompatibilityEnum;

public class ServiceDTO {

    private java.lang.String item;
    private Double price;
    private boolean available;
    private CatalogCompatibilityEnum compatibility;

    public ServiceDTO(java.lang.String item, Double price, boolean available, CatalogCompatibilityEnum compatibility) {
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

    public CatalogCompatibilityEnum getCompatibility() {
        return compatibility;
    }

    public void setItem(java.lang.String item) {
        this.item = item;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setCompatibility(CatalogCompatibilityEnum compatibility) {
        this.compatibility = compatibility;
    }
}
