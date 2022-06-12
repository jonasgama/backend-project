package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.entity.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceForDeviceRepository extends CrudRepository<ServiceEntity, String> {

}
