package com.ninjaone.backendinterviewproject.usecase;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.ServiceForDeviceRepository;
import com.ninjaone.backendinterviewproject.database.ServicePurchaseRepository;
import com.ninjaone.backendinterviewproject.entity.CustomerDeviceServicesEntity;
import com.ninjaone.backendinterviewproject.entity.CustomerDevicesEntity;
import com.ninjaone.backendinterviewproject.entity.ServiceEntity;
import com.ninjaone.backendinterviewproject.enums.Compatibility;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicePurchaseUseCase {
    private final ServiceForDeviceRepository serviceForDeviceRepository;
    private final ServicePurchaseRepository servicePurchaseRepository;


    public ServicePurchaseUseCase(
            ServiceForDeviceRepository serviceForDeviceRepository,
            ServicePurchaseRepository servicePurchaseRepository
    ) {
        this.serviceForDeviceRepository = serviceForDeviceRepository;
        this.servicePurchaseRepository = servicePurchaseRepository;
    }

    public void purchaseServiceForDevice(CustomerDevicesEntity customerDevice, String serviceForDeviceId) throws Exception {

        Optional<ServiceEntity> serviceForDevice = serviceForDeviceRepository.findById(serviceForDeviceId);
        if(!serviceForDevice.isPresent() && serviceForDevice.get().isAvailable()){
            throw new Exception("service is not available for this device");
        }

        if(!customerDevice.getDevice().getCompatibility().equals(serviceForDevice.get().getCompatibility())
        && !customerDevice.getDevice().getCompatibility().equals(Compatibility.GENERIC)){
            throw new Exception("service is not compatible for this device");
        }

        CustomerDeviceServicesEntity servicePurchased = new CustomerDeviceServicesEntity();
        servicePurchased.setDevicePurchased(customerDevice);
        servicePurchased.setHiredService(serviceForDevice.get());

        servicePurchaseRepository.save(servicePurchased);

    }


}
