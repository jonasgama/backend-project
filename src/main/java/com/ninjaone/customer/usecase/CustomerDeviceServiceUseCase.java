package com.ninjaone.customer.usecase;

import com.ninjaone.catalog.infra.repository.ServiceForDeviceRepository;
import com.ninjaone.customer.infra.repository.CustomerDeviceServiceRepository;
import com.ninjaone.customer.infra.entity.CustomerDeviceServicesEntity;
import com.ninjaone.customer.infra.entity.CustomerDevicesEntity;
import com.ninjaone.catalog.infra.entity.ServiceEntity;
import com.ninjaone.catalog.domain.enums.CatalogCompatibilityEnum;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerDeviceServiceUseCase {
    private final ServiceForDeviceRepository serviceForDeviceRepository;
    private final CustomerDeviceServiceRepository customerDeviceServiceRepository;


    public CustomerDeviceServiceUseCase(
            ServiceForDeviceRepository serviceForDeviceRepository,
            CustomerDeviceServiceRepository customerDeviceServiceRepository
    ) {
        this.serviceForDeviceRepository = serviceForDeviceRepository;
        this.customerDeviceServiceRepository = customerDeviceServiceRepository;
    }

    public CustomerDeviceServicesEntity purchaseServiceForDevice(CustomerDevicesEntity customerDevice, String serviceForDeviceId) throws Exception {

        Optional<ServiceEntity> service = serviceForDeviceRepository.findById(serviceForDeviceId);

        if(!service.isPresent()){
            throw new Exception("service not found");
        }

        if(!customerDevice.getDevice().getCompatibility().equals(service.get().getCompatibility())
                && !service.get().getCompatibility().equals(CatalogCompatibilityEnum.GENERIC)){
            throw new Exception("service is not compatible for this device");
        }

        CustomerDeviceServicesEntity servicePurchased = new CustomerDeviceServicesEntity();
        servicePurchased.setDevicePurchased(customerDevice);
        servicePurchased.setHiredService(service.get());
        servicePurchased.setPrice(service.get().getPrice());

        return customerDeviceServiceRepository.save(servicePurchased);

    }


}
