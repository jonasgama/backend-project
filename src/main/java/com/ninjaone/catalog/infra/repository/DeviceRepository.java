package com.ninjaone.catalog.infra.repository;

import com.ninjaone.catalog.infra.entity.DeviceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceRepository extends CrudRepository<DeviceEntity, String> {


}
