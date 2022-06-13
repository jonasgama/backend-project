package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.core.dto.DeviceDTO;
import com.ninjaone.backendinterviewproject.catalog.entity.DeviceEntity;
import com.ninjaone.backendinterviewproject.catalog.usecase.DeviceUseCase;
import org.springframework.http.HttpStatus;
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
    private void post(@RequestBody DeviceDTO device) throws Exception {
        useCase.insert(device);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private DeviceEntity get(@PathVariable String id) throws Exception {
        return useCase.get(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void put(@PathVariable String id, @RequestBody Double price) throws Exception {
        useCase.update(id, price);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable String id){
        useCase.delete(id);
    }
}
