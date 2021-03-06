package com.ninjaone.order.usecase;

import com.ninjaone.catalog.domain.enums.CatalogEnum;
import com.ninjaone.order.domain.enums.OperationEnum;
import com.ninjaone.order.infra.repository.OrderRepository;
import com.ninjaone.order.infra.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderTransactionUseCase {
    private final OrderRepository repository;

    public OrderTransactionUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    public void insert(
     Integer customerCatalogId,
     UUID traceId,
     String customerName,
     String item,
     Double price,
     CatalogEnum catalog){
        OrderEntity order = new OrderEntity();
        order.setPrice(price);
        order.setCatalogItem(item);
        order.setCustomerName(customerName);
        order.setCreatedAt(LocalDateTime.now());
        order.setCatalogType(catalog);
        order.setOperation(OperationEnum.PURHCASE);
        order.setTraceId(traceId);
        order.setCustomerCatalogId(customerCatalogId);
        repository.save(order);
    }

    public Set<OrderEntity> filterByMonth(String customerId, int year, int month){
        LocalDateTime startOfTheMonth = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime endOfTheMonth = startOfTheMonth.withDayOfMonth(startOfTheMonth.getMonth().length(startOfTheMonth.toLocalDate().isLeapYear()));
        LocalDateTime endOfTimeMonth = endOfTheMonth.toLocalDate().atTime(LocalTime.MAX);
        return repository.findByCustomerNameAndCreatedAtBetween(customerId, startOfTheMonth, endOfTimeMonth);
    }

}
