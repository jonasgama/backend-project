package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.entity.CustomerDeviceServicesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicePurchaseRepository extends CrudRepository<CustomerDeviceServicesEntity, String> {}
