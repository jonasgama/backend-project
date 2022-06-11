package com.ninjaone.backendinterviewproject.usecase;

import com.ninjaone.backendinterviewproject.dto.PurchaseDTO;
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
    public void confirmPurchase(PurchaseDTO purchase) throws Exception {

        CustomerDevicesEntity customerDevice = devicePurchase.purchaseDeviceForCustomer(purchase.getCustomerId(), purchase.getDeviceId());
        servicePurchase.purchaseServiceForDevice(customerDevice, purchase.getServiceForDevice());

        transactionService.insert(purchase.getCustomerId(), purchase.getDeviceId(), 0d);
    }



}
