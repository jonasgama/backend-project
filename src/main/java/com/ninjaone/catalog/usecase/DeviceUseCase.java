package com.ninjaone.catalog.usecase;

import com.ninjaone.catalog.domain.dto.CatalogDTO;
import com.ninjaone.catalog.infra.repository.DeviceRepository;
import com.ninjaone.catalog.infra.entity.DeviceEntity;
import org.springframework.stereotype.Service;

@Service
public class DeviceUseCase{

    private final DeviceRepository repository;

    public DeviceUseCase(DeviceRepository repository) {
        this.repository = repository;
    }

    public void insert(CatalogDTO dto) throws Exception {
        repository.findById(dto.getItem())
                .ifPresentOrElse(item -> {
                            new Exception(String.format("device %s already saved", item.getItem()));
                        },
                        ()->  repository.save(new DeviceEntity(dto.getItem(), dto.getPrice(), dto.getCompatibility())));
    }

    public void delete(String id) {
        repository.findById(id)
                .ifPresentOrElse(item -> {
                            item.setAvailable(false);
                            repository.save(item);
                        },
                        ()->new Exception("device not found!"));
    }

    public void update(String id, CatalogDTO dto) {
         repository.findById(id)
                .ifPresentOrElse(device -> {
                    device.setPrice(dto.getPrice());
                    repository.save(device);
                },
                ()->new Exception("device not found!"));
    }

    public CatalogDTO get(String id) throws Exception {
        return repository.findById(id)
                .map(entity -> new CatalogDTO(entity.getItem(), entity.getPrice(), entity.isAvailable(), entity.getCompatibility().getValue()))
                .orElseThrow(()->new Exception("device not found!"));
    }
}
