package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.DeviceDTO;
import com.ninjaone.backendinterviewproject.entity.DeviceEntity;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService service;

    public DeviceController(DeviceService useCase) {
        this.service = useCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void post(@RequestBody DeviceDTO device){
        service.save(device);
    }

    @GetMapping("/{id}")
    private DeviceEntity get(@PathVariable String id){
        return service.get(id)
                .orElseThrow();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void put(@PathVariable String id, @RequestBody Double price) throws Exception {
        service.update(id, price);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable String id){
        service.delete(id);
    }
}
