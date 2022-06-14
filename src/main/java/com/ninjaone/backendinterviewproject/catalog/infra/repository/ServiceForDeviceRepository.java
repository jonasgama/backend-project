package com.ninjaone.backendinterviewproject.catalog.infra.repository;

import com.ninjaone.backendinterviewproject.catalog.infra.entity.entity.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceForDeviceRepository extends CrudRepository<ServiceEntity, String> {

}
