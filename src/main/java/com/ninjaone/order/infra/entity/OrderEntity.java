package com.ninjaone.order.infra.entity;

import com.ninjaone.catalog.domain.enums.CatalogEnum;
import com.ninjaone.order.domain.enums.OperationEnum;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="ORDERS")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String customerName;
    private String catalogItem;
    private Double price;
    private UUID traceId;
    private Integer customerCatalogId;
    private OperationEnum operation;
    private CatalogEnum catalogType;
    @CreationTimestamp
    private LocalDateTime createdAt;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCatalogItem() {
        return catalogItem;
    }

    public void setCatalogItem(String catalogItem) {
        this.catalogItem = catalogItem;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getTraceId() {
        return traceId;
    }

    public void setTraceId(UUID traceId) {
        this.traceId = traceId;
    }

    public OperationEnum getOperation() {
        return operation;
    }

    public void setOperation(OperationEnum operation) {
        this.operation = operation;
    }

    public CatalogEnum getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(CatalogEnum catalogType) {
        this.catalogType = catalogType;
    }

    public Integer getCustomerCatalogId() {
        return customerCatalogId;
    }

    public void setCustomerCatalogId(Integer customerCatalogId) {
        this.customerCatalogId = customerCatalogId;
    }

}
