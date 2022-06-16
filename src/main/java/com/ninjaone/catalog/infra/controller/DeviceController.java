package com.ninjaone.catalog.infra.controller;

import com.ninjaone.catalog.domain.dto.CatalogDTO;
import com.ninjaone.catalog.domain.dto.contraints.CreateCatalog;
import com.ninjaone.catalog.domain.dto.contraints.UpdateCatalog;
import com.ninjaone.catalog.infra.entity.CatalogEntity;
import com.ninjaone.catalog.infra.entity.DeviceEntity;
import com.ninjaone.catalog.usecase.DeviceUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceUseCase useCase;

    public DeviceController(DeviceUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void post(@Validated(CreateCatalog.class) @RequestBody CatalogDTO device) throws Exception {
        useCase.insert(device);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private CatalogDTO get(@PathVariable String id) throws Exception {
        return useCase.get(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void put(@PathVariable String id, @Validated(UpdateCatalog.class) @RequestBody CatalogDTO dto) throws Exception {
        useCase.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable String id){
        useCase.delete(id);
    }
}
