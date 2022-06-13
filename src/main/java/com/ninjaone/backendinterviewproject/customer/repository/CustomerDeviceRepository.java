package com.ninjaone.backendinterviewproject.customer.repository;

import com.ninjaone.backendinterviewproject.customer.entity.CustomerDevicesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDeviceRepository extends CrudRepository<CustomerDevicesEntity, Integer> {}
