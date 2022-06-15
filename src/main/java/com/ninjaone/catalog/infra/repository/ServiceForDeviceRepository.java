package com.ninjaone.catalog.infra.repository;

import com.ninjaone.catalog.infra.entity.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceForDeviceRepository extends CrudRepository<ServiceEntity, String> {

}
