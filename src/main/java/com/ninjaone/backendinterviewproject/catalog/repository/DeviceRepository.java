package com.ninjaone.backendinterviewproject.catalog.repository;

import com.ninjaone.backendinterviewproject.catalog.entity.DeviceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceRepository extends CrudRepository<DeviceEntity, String> {


}
