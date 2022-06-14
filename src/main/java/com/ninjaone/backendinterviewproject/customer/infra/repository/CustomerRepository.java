package com.ninjaone.backendinterviewproject.customer.infra.repository;

import com.ninjaone.backendinterviewproject.customer.infra.entity.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {}
