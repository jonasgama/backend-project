package com.ninjaone.order.domain.dto;

import com.ninjaone.order.infra.entity.OrderEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderTransactionDTO {

    private double totalPaid;
    private Integer month;
    private Integer year;
    private Map<String, Double> summary;
    private Set<OrderTransactionItemDTO> items;


    public OrderTransactionDTO(Integer year, Integer month){
        this.items = new HashSet<>();
        this.summary = new HashMap<>();
        this.month = month;
        this.year = year;
    }

    public void parse(Set<OrderEntity> entities){


        for (OrderEntity entity : entities) {

            OrderTransactionItemDTO item = new OrderTransactionItemDTO(
                    entity.getCatalogItem(),
                    entity.getCustomerName(),
                    entity.getPrice(),
                    entity.getOperation(),
                    entity.getCatalogType(),
                    entity.getCreatedAt());

            this.items.add(item);
            this.totalPaid += item.getPrice();

            summary.computeIfPresent(item.getItem(), (s, aDouble) -> aDouble + item.getPrice());
            summary.computeIfAbsent(item.getItem(), s -> item.getPrice());
        }
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public Set<OrderTransactionItemDTO> getItems() {
        return items;
    }

    public Map<String, Double> getSummary() {
        return summary;
    }
}
