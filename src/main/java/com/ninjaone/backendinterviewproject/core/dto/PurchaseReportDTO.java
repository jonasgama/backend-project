package com.ninjaone.backendinterviewproject.core.dto;

import com.ninjaone.backendinterviewproject.core.enums.CatalogEnum;
import com.ninjaone.backendinterviewproject.core.enums.OperationEnum;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class PurchaseReportDTO {

    private Double totalPaid;
    private String month;
    private String year;
    private Set<PurchaseReportItemDTO> items;
}
