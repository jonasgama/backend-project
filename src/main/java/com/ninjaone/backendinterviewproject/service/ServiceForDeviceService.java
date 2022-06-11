package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceForDeviceRepository;
import com.ninjaone.backendinterviewproject.entity.ServiceEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceForDeviceService {
    private final ServiceForDeviceRepository deviceRepository;

    public ServiceForDeviceService(ServiceForDeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public ServiceEntity insert(ServiceEntity serviceForDevice){
        return deviceRepository.save(serviceForDevice);
    }

    public void delete(String id) {
        deviceRepository.deleteById(id);
    }
}
