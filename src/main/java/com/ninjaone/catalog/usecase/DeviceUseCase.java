package com.ninjaone.catalog.usecase;

import com.ninjaone.catalog.domain.dto.CatalogDTO;
import com.ninjaone.catalog.infra.repository.DeviceRepository;
import com.ninjaone.catalog.infra.entity.DeviceEntity;
import org.springframework.stereotype.Service;

@Service
public class DeviceUseCase {
    private final DeviceRepository repository;

    public DeviceUseCase(DeviceRepository repository) {
        this.repository = repository;
    }

    public void insert(CatalogDTO dto) throws Exception {
        repository.findById(dto.getItem())
                .ifPresentOrElse(device -> {
                            new Exception(String.format("device %s already saved", device.getItem()));
                        },
                        ()->  repository.save(new DeviceEntity(dto.getItem(), dto.getPrice(), dto.getCompatibility())));
    }

    public void update(String id, CatalogDTO dto) {
         repository.findById(id)
                .ifPresentOrElse(device -> {
                    device.setPrice(dto.getPrice());
                    repository.save(device);
                },
                ()->new Exception("device not found!"));
    }

    public DeviceEntity get(String id) throws Exception {
        return repository.findById(id)
                .orElseThrow(()->new Exception("device not found!"));
    }

    public void delete(String id) {
        repository.findById(id)
                .ifPresentOrElse(device -> {
                            device.setAvailable(false);
                            repository.save(device);
                        },
                        ()->new Exception("device not found!"));
    }
}
