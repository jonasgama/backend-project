package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.dto.DeviceDTO;
import com.ninjaone.backendinterviewproject.entity.DeviceEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    public DeviceEntity save(DeviceDTO dto){
        DeviceEntity entity = new DeviceEntity(dto.getItem(), dto.getPrice(), dto.isAvailable(), dto.getCompatibility());
        return repository.save(entity);
    }

    public void update(String id, Double price) {
         repository.findById(id)
                .ifPresentOrElse(device -> {
                    device.setPrice(price);
                    repository.save(device);
                },
                ()->new Exception("device not found!"));
    }

    public Optional<DeviceEntity> get(String id){
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
