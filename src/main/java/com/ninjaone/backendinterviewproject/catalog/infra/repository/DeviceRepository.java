package com.ninjaone.backendinterviewproject.catalog.infra.repository;

import com.ninjaone.backendinterviewproject.catalog.infra.entity.entity.DeviceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceRepository extends CrudRepository<DeviceEntity, String> {


}
