package com.ninjaone.backendinterviewproject.shop.transaction.repository;

import com.ninjaone.backendinterviewproject.shop.transaction.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    Set<TransactionEntity> findAllByCreatedAtBetween(LocalDateTime init, LocalDateTime end);
}
