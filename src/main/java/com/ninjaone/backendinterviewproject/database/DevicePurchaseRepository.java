package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.entity.CustomerDevicesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicePurchaseRepository extends CrudRepository<CustomerDevicesEntity, Integer> {}
