package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {}
