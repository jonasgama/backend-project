package com.ninjaone.backendinterviewproject.catalog.usecase;

import com.ninjaone.backendinterviewproject.catalog.entity.ServiceEntity;
import com.ninjaone.backendinterviewproject.catalog.repository.ServiceForDeviceRepository;
import com.ninjaone.backendinterviewproject.core.dto.ServiceDTO;
import org.springframework.stereotype.Service;

@Service
public class ServiceUseCase {
    private final ServiceForDeviceRepository repository;

    public ServiceUseCase(ServiceForDeviceRepository repository) {
        this.repository = repository;
    }

    public void insert(ServiceDTO dto) throws Exception {
        repository.findById(dto.getItem())
                .ifPresentOrElse(service -> {
                            new Exception(String.format("service %s already saved", service.getItem()));
                        },
                        ()->  repository.save(new ServiceEntity(dto.getItem(), dto.getPrice(), dto.getCompatibility())));
    }

    public void delete(String id) {
        repository.findById(id)
                .ifPresentOrElse(service -> {
                            service.setAvailable(false);
                            repository.save(service);
                        },
                        ()->new Exception("service not found!"));
    }
}
