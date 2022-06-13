package com.ninjaone.backendinterviewproject.catalog.repository;

import com.ninjaone.backendinterviewproject.catalog.entity.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceForDeviceRepository extends CrudRepository<ServiceEntity, String> {

}
