package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.entity.DeviceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceRepository extends CrudRepository<DeviceEntity, String> {


}
