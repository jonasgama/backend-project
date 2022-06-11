package com.ninjaone.backendinterviewproject.usecase;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.database.DevicePurchaseRepository;
import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.entity.CustomerEntity;
import com.ninjaone.backendinterviewproject.entity.DeviceEntity;
import com.ninjaone.backendinterviewproject.entity.CustomerDevicesEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDevicesPurchaseUseCase {

    private final DevicePurchaseRepository devicePurchaseRepository;
    private final DeviceRepository deviceRepository;
    private final CustomerRepository customerRepository;

    public CustomerDevicesPurchaseUseCase(
            DevicePurchaseRepository devicePurchaseRepository,
            DeviceRepository deviceRepository,
            CustomerRepository customerRepository
    ) {
        this.devicePurchaseRepository = devicePurchaseRepository;
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
    }

    public CustomerDevicesEntity purchaseDeviceForCustomer(String customerId, String deviceId) throws Exception {
        Optional<CustomerEntity> customer = customerRepository.findById(customerId);
        if(!customer.isPresent()){
            throw new Exception("customer not found");
        }
        Optional<DeviceEntity> device = deviceRepository.findById(deviceId);
        if(!device.isPresent() && device.get().isAvailable()){
            throw new Exception("device not found");
        }
        CustomerDevicesEntity devicePurchase = new CustomerDevicesEntity();
        devicePurchase.setCustomer(customer.get());
        devicePurchase.setDevice(device.get());

        return devicePurchaseRepository.save(devicePurchase);
    }

}
