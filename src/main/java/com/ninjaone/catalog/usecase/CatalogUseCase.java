package com.ninjaone.catalog.usecase;

import com.ninjaone.catalog.domain.dto.CatalogDTO;
import com.ninjaone.catalog.infra.entity.CatalogEntity;
import com.ninjaone.catalog.infra.entity.ServiceEntity;
import com.ninjaone.catalog.infra.repository.ServiceForDeviceRepository;
import org.springframework.data.repository.CrudRepository;

public abstract class CatalogUseCase {

    private CrudRepository<CatalogEntity, String> repository;

    abstract String catalogName();

    public CatalogUseCase(CrudRepository repository) {
        this.repository = repository;
    }

    public void insert(CatalogDTO dto) throws Exception {
        repository.findById(dto.getItem())
                .ifPresentOrElse(item -> {
                            new Exception(String.format("%s %s already saved", catalogName(), item.getItem()));
                        },
                        ()->  repository.save(new ServiceEntity(dto.getItem(), dto.getPrice(), dto.getCompatibility())));
    }

    public void delete(String id) {
        repository.findById(id)
                .ifPresentOrElse(item -> {
                            item.setAvailable(false);
                            repository.save(item);
                        },
                        ()->new Exception(String.format("%s not found!",  catalogName())));
    }
}
