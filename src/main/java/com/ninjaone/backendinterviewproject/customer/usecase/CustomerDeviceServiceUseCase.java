package com.ninjaone.backendinterviewproject.customer.usecase;

import com.ninjaone.backendinterviewproject.catalog.entity.DeviceEntity;
import com.ninjaone.backendinterviewproject.catalog.repository.ServiceForDeviceRepository;
import com.ninjaone.backendinterviewproject.customer.repository.CustomerDeviceServiceRepository;
import com.ninjaone.backendinterviewproject.customer.entity.CustomerDeviceServicesEntity;
import com.ninjaone.backendinterviewproject.customer.entity.CustomerDevicesEntity;
import com.ninjaone.backendinterviewproject.catalog.entity.ServiceEntity;
import com.ninjaone.backendinterviewproject.core.enums.CompatibilityEnum;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerDeviceServiceUseCase {
    private final ServiceForDeviceRepository serviceForDeviceRepository;
    private final CustomerDeviceServiceRepository servicePurchaseRepository;


    public CustomerDeviceServiceUseCase(
            ServiceForDeviceRepository serviceForDeviceRepository,
            CustomerDeviceServiceRepository servicePurchaseRepository
    ) {
        this.serviceForDeviceRepository = serviceForDeviceRepository;
        this.servicePurchaseRepository = servicePurchaseRepository;
    }

    public CustomerDeviceServicesEntity purchaseServiceForDevice(UUID traceId, CustomerDevicesEntity customerDevice, String serviceForDeviceId) throws Exception {

        Optional<ServiceEntity> service = serviceForDeviceRepository.findById(serviceForDeviceId);

        if(!service.isPresent()){
            throw new Exception("service not found");
        }

        if(!customerDevice.getDevice().getCompatibility().equals(service.get().getCompatibility())
                && !service.get().getCompatibility().equals(CompatibilityEnum.GENERIC)){
            throw new Exception("service is not compatible for this device");
        }

        CustomerDeviceServicesEntity servicePurchased = new CustomerDeviceServicesEntity();
        servicePurchased.setDevicePurchased(customerDevice);
        servicePurchased.setHiredService(service.get());
        servicePurchased.setTraceId(traceId);

        return servicePurchaseRepository.save(servicePurchased);

    }


}
