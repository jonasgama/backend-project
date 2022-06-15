package com.ninjaone.customer.infra.repository;

import com.ninjaone.customer.infra.entity.CustomerDevicesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDeviceRepository extends CrudRepository<CustomerDevicesEntity, Integer> {}
