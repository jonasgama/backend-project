package com.ninjaone.backendinterviewproject.catalog.domain.dto;

import com.ninjaone.backendinterviewproject.catalog.domain.enums.CatalogCompatibilityEnum;
import com.ninjaone.backendinterviewproject.catalog.domain.enums.pattern.CompatibilityEnumPattern;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class CatalogDTO {

    @NotEmpty
    private String item;
    @Min(value = 1)
    private Double price;
    private boolean available;
    @CompatibilityEnumPattern(enumClass= CatalogCompatibilityEnum.class)
    private String compatibility;

    public CatalogDTO(){
        this.available = true;
    }

    public CatalogDTO(String item, Double price, boolean available, String compatibility) {
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

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

}
