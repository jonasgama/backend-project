package com.ninjaone.order.domain.dto;

import com.ninjaone.catalog.domain.enums.CatalogEnum;
import com.ninjaone.order.domain.enums.OperationEnum;

import java.time.LocalDateTime;

public class OrderTransactionItemDTO {

    private String item;
    private String customerId;
    private Double price;
    private OperationEnum operation;
    private CatalogEnum catalogType;
    private LocalDateTime createdAt;

    public OrderTransactionItemDTO(
         String item,
         String customerId,
         Double price,
         OperationEnum operation,
         CatalogEnum catalogType,
         LocalDateTime createdAt
    ){
        this.item = item;
        this.customerId = customerId;
        this.price = price;
        this.operation = operation;
        this.catalogType = catalogType;
        this.createdAt = createdAt;
    }

    public Double getPrice() {
        return price;
    }

    public String getItem() {
        return item;
    }

    public String getCustomerId() {
        return customerId;
    }

    public OperationEnum getOperation() {
        return operation;
    }

    public CatalogEnum getCatalogType() {
        return catalogType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
