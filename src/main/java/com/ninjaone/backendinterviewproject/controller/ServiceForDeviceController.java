package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.ServiceDTO;
import com.ninjaone.backendinterviewproject.entity.ServiceEntity;
import com.ninjaone.backendinterviewproject.service.ServiceForDeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServiceForDeviceController {
    private final ServiceForDeviceService service;

    public ServiceForDeviceController(ServiceForDeviceService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ServiceEntity post(@RequestBody ServiceDTO dto){
        //return service.insert(dto.getItem());
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable String id){
        service.delete(id);
    }
}
