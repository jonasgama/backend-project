package com.ninjaone.order.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

public class OrderDTO {

    @JsonProperty("customer_id")
    private String customerId;

    private Set<OrderItemsDTO> items;


    public OrderDTO(){
        this.items = new HashSet<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Set<OrderItemsDTO> getItems() {
        return items;
    }

    public void setItems(Set<OrderItemsDTO> items) {
        this.items = items;
    }

    public void addItems(OrderItemsDTO... items) {
        this.items.addAll(Set.of(items));
    }
}
