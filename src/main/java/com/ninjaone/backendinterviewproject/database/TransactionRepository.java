package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.entity.TransactionsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionsEntity, Integer> {}
