package com.ninjaone.backendinterviewproject.shop.transaction.usecase;

import com.ninjaone.backendinterviewproject.core.enums.CatalogEnum;
import com.ninjaone.backendinterviewproject.core.enums.OperationEnum;
import com.ninjaone.backendinterviewproject.shop.transaction.entity.TransactionEntity;
import com.ninjaone.backendinterviewproject.shop.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Service
public class TransactionUseCase {
    private final TransactionRepository repository;

    public TransactionUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public void insert(
     UUID traceId,
     String customerName,
     String item,
     Double price,
     CatalogEnum catalog){
        TransactionEntity transactionsEntity = new TransactionEntity();
        transactionsEntity.setPrice(price);
        transactionsEntity.setCatalogItem(item);
        transactionsEntity.setCustomerName(customerName);
        transactionsEntity.setCreatedAt(LocalDateTime.now());
        transactionsEntity.setCatalogType(catalog);
        transactionsEntity.setOperation(OperationEnum.PURHCASE);
        transactionsEntity.setTraceId(traceId);
        repository.save(transactionsEntity);
    }

    public Set<TransactionEntity> filterByMonth(int year, int month){
        LocalDateTime startOfTheMonth = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime endOfTheMonth = startOfTheMonth.withDayOfMonth(startOfTheMonth.getMonth().length(startOfTheMonth.toLocalDate().isLeapYear()));
        LocalDateTime endOfTimeMonth = endOfTheMonth.toLocalDate().atTime(LocalTime.MAX);
        return repository.findAllByCreatedAtBetween(startOfTheMonth, endOfTimeMonth);
    }

}
