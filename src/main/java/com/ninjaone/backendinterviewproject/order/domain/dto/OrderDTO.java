package com.ninjaone.backendinterviewproject.order.domain.dto;


import java.util.HashSet;
import java.util.Set;

public class OrderDTO {

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
