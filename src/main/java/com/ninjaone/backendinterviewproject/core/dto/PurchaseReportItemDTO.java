package com.ninjaone.backendinterviewproject.core.dto;

import com.ninjaone.backendinterviewproject.core.enums.CatalogEnum;
import com.ninjaone.backendinterviewproject.core.enums.OperationEnum;

import java.time.LocalDateTime;

public class PurchaseReportItemDTO {

    private Double totalPaid;
    private String customerName;
    private String item;
    private Double price;
    private OperationEnum operation;
    private CatalogEnum catalogType;
    private LocalDateTime createdAt;
}
