package com.ninjaone.backendinterviewproject.catalog.infra.controller;

import com.ninjaone.backendinterviewproject.catalog.domain.dto.ServiceDTO;
import com.ninjaone.backendinterviewproject.catalog.usecase.ServiceUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServiceForDeviceController {
    private final ServiceUseCase useCase;

    public ServiceForDeviceController(ServiceUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void post(@RequestBody ServiceDTO dto) throws Exception {
         useCase.insert(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable String id){
        useCase.delete(id);
    }
}
