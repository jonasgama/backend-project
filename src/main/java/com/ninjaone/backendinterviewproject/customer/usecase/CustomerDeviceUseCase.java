package com.ninjaone.backendinterviewproject.customer.usecase;

import com.ninjaone.backendinterviewproject.customer.repository.CustomerRepository;
import com.ninjaone.backendinterviewproject.customer.repository.CustomerDeviceRepository;
import com.ninjaone.backendinterviewproject.catalog.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.customer.entity.CustomerEntity;
import com.ninjaone.backendinterviewproject.catalog.entity.DeviceEntity;
import com.ninjaone.backendinterviewproject.customer.entity.CustomerDevicesEntity;
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

    public CustomerDevicesEntity purchaseDeviceForCustomer(UUID traceId, String customerId, String deviceId) throws Exception {
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
        devicePurchase.setTraceId(traceId);

        return devicePurchaseRepository.save(devicePurchase);
    }

}
