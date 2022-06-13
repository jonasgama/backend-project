package com.ninjaone.backendinterviewproject.customer.repository;

import com.ninjaone.backendinterviewproject.customer.entity.CustomerDeviceServicesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDeviceServiceRepository extends CrudRepository<CustomerDeviceServicesEntity, String> {}
