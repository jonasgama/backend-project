package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.TransactionRepository;
import com.ninjaone.backendinterviewproject.entity.TransactionsEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
        transactionsEntity.setCreatedAt(LocalDate.now());
        repository.save(transactionsEntity);
    }

}
