package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.TransactionRepository;
import com.ninjaone.backendinterviewproject.entity.TransactionsEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void insert(
     String customerName,
     String item,
     Double price){
        TransactionsEntity transactionsEntity = new TransactionsEntity();
        transactionsEntity.setDevicePrice(price);
        transactionsEntity.setDevice(item);
        transactionsEntity.setCustomerName(customerName);
        transactionsEntity.setCreatedAt(LocalDateTime.now());
        repository.save(transactionsEntity);
    }

    public Set<TransactionsEntity> filterByMonth(int year, int month){
        LocalDateTime startOfTheMonth = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime endOfTheMonth = startOfTheMonth.withDayOfMonth(startOfTheMonth.getMonth().length(startOfTheMonth.toLocalDate().isLeapYear()));
        LocalDateTime endOfTimeMonth = endOfTheMonth.toLocalDate().atTime(LocalTime.MAX);
        return repository.findAllByCreatedAtBetween(startOfTheMonth, endOfTimeMonth);
    }

}
