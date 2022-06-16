package com.ninjaone.customer.usecase;

import com.ninjaone.customer.infra.repository.CustomerRepository;
import com.ninjaone.customer.infra.repository.CustomerDeviceRepository;
import com.ninjaone.catalog.infra.repository.DeviceRepository;
import com.ninjaone.customer.infra.entity.CustomerEntity;
import com.ninjaone.catalog.infra.entity.DeviceEntity;
import com.ninjaone.customer.infra.entity.CustomerDevicesEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerDeviceUseCase {

    private final CustomerDeviceRepository devicePurchaseRepository;
    private final DeviceRepository deviceRepository;
    private final CustomerRepository customerRepository;

    public CustomerDeviceUseCase(
            CustomerDeviceRepository devicePurchaseRepository,
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
        if(!device.isPresent()){
            throw new Exception("device not found");
        }

        CustomerDevicesEntity devicePurchase = new CustomerDevicesEntity();
        devicePurchase.setCustomer(customer.get());
        devicePurchase.setDevice(device.get());
        devicePurchase.setPrice(device.get().getPrice());

        return devicePurchaseRepository.save(devicePurchase);
    }

}
