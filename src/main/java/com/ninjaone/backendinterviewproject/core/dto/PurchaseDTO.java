package com.ninjaone.backendinterviewproject.core.dto;


import java.util.HashSet;
import java.util.Set;

public class PurchaseDTO {

    private String customerId;

    private Set<PurchaseItemsDTO> items;


    public PurchaseDTO(){
        this.items = new HashSet<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Set<PurchaseItemsDTO> getItems() {
        return items;
    }

    public void setItems(Set<PurchaseItemsDTO> items) {
        this.items = items;
    }

    public void addItems(PurchaseItemsDTO ... items) {
        this.items.addAll(Set.of(items));
    }
}
