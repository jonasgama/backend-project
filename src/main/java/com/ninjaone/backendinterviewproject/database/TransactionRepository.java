package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.entity.TransactionsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionsEntity, Integer> {
    Set<TransactionsEntity> findAllByCreatedAtBetween(LocalDateTime init, LocalDateTime end);
}
