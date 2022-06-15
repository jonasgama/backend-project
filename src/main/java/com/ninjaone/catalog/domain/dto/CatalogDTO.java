package com.ninjaone.catalog.domain.dto;

import com.ninjaone.catalog.domain.dto.contraints.CreateCatalog;
import com.ninjaone.catalog.domain.dto.contraints.UpdateCatalog;
import com.ninjaone.catalog.domain.enums.CatalogCompatibilityEnum;
import com.ninjaone.catalog.domain.enums.pattern.CompatibilityEnumPattern;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class CatalogDTO {

    @NotEmpty(groups = {CreateCatalog.class}, message = "catalog id not provided")
    private String item;
    @Min(groups = {UpdateCatalog.class, CreateCatalog.class}, value = 1, message = "provide minimum value")
    private double price;
    private boolean available;
    @CompatibilityEnumPattern(groups = {UpdateCatalog.class, CreateCatalog.class}, enumClass= CatalogCompatibilityEnum.class, message = "compatibility is not valid")
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
