package com.ninjaone.catalog.infra.controller;

import com.ninjaone.catalog.domain.dto.CatalogDTO;
import com.ninjaone.catalog.domain.dto.contraints.CreateCatalog;
import com.ninjaone.catalog.domain.dto.contraints.UpdateCatalog;
import com.ninjaone.catalog.usecase.ServiceUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/services")
public class ServiceForDeviceController {
    private final ServiceUseCase useCase;

    public ServiceForDeviceController(ServiceUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void post(@Validated(CreateCatalog.class) @RequestBody CatalogDTO dto) throws Exception {
         useCase.insert(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable String id){
        useCase.delete(id);
    }
}
