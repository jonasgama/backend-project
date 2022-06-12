package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceForDeviceRepository;
import com.ninjaone.backendinterviewproject.dto.DeviceDTO;
import com.ninjaone.backendinterviewproject.dto.ServiceDTO;
import com.ninjaone.backendinterviewproject.entity.ServiceEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ServiceForDeviceService {
    private final ServiceForDeviceRepository repository;

    public ServiceForDeviceService(ServiceForDeviceRepository deviceRepository) {
        this.repository = deviceRepository;
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
