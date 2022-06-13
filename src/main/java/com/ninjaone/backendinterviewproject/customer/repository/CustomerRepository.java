package com.ninjaone.backendinterviewproject.customer.repository;

import com.ninjaone.backendinterviewproject.customer.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {}
