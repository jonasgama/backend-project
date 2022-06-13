package com.ninjaone.backendinterviewproject.catalog.usecase;

import com.ninjaone.backendinterviewproject.catalog.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.core.dto.DeviceDTO;
import com.ninjaone.backendinterviewproject.catalog.entity.DeviceEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceUseCase {
    private final DeviceRepository repository;

    public DeviceUseCase(DeviceRepository repository) {
        this.repository = repository;
    }

    public void insert(DeviceDTO dto) throws Exception {
        repository.findById(dto.getItem())
                .ifPresentOrElse(device -> {
                            new Exception(String.format("device %s already saved", device.getItem()));
                        },
                        ()->  repository.save(new DeviceEntity(dto.getItem(), dto.getPrice(), dto.getCompatibility())));
    }

    public void update(String id, Double price) {
         repository.findById(id)
                .ifPresentOrElse(device -> {
                    device.setPrice(price);
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
