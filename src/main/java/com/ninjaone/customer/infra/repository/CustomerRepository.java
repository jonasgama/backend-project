package com.ninjaone.customer.infra.repository;

import com.ninjaone.customer.infra.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {}
