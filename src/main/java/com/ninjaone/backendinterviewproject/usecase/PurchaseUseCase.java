package com.ninjaone.backendinterviewproject.usecase;

import com.ninjaone.backendinterviewproject.dto.PurchaseDTO;
import com.ninjaone.backendinterviewproject.dto.PurchaseItemsDTO;
import com.ninjaone.backendinterviewproject.entity.CustomerDeviceServicesEntity;
import com.ninjaone.backendinterviewproject.entity.CustomerDevicesEntity;
import com.ninjaone.backendinterviewproject.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseUseCase {


    private final CustomerDevicesPurchaseUseCase devicePurchase;
    private final ServicePurchaseUseCase servicePurchase;
    private final TransactionService transactionService;

    public PurchaseUseCase(CustomerDevicesPurchaseUseCase devicePurchase,
                           ServicePurchaseUseCase servicePurchase,
                           TransactionService transactionService) {
        this.devicePurchase = devicePurchase;
        this.servicePurchase = servicePurchase;
        this.transactionService = transactionService;
    }

    @Transactional
    public void includeDevicesAndServices(PurchaseDTO purchase) throws Exception {

        for (PurchaseItemsDTO item : purchase.getItems()) {
            CustomerDevicesEntity customerDevicesEntity = devicePurchase.purchaseDeviceForCustomer(purchase.getCustomerId(), item.getDeviceId());
            transactionService.insert(purchase.getCustomerId(), item.getDeviceId(), customerDevicesEntity.getDevice().getPrice());
            for (String service : item.getServices()) {
                CustomerDeviceServicesEntity customerDeviceServicesEntity = servicePurchase.purchaseServiceForDevice(customerDevicesEntity, service);
                transactionService.insert(purchase.getCustomerId(), service, customerDeviceServicesEntity.getHiredService().getPrice());
            }
        }
    }
}
