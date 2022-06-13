package com.ninjaone.backendinterviewproject.shop.transaction.usecase;

import com.ninjaone.backendinterviewproject.customer.usecase.CustomerDeviceUseCase;
import com.ninjaone.backendinterviewproject.customer.usecase.CustomerDeviceServiceUseCase;
import com.ninjaone.backendinterviewproject.core.dto.PurchaseDTO;
import com.ninjaone.backendinterviewproject.core.dto.PurchaseItemsDTO;
import com.ninjaone.backendinterviewproject.customer.entity.CustomerDeviceServicesEntity;
import com.ninjaone.backendinterviewproject.customer.entity.CustomerDevicesEntity;
import com.ninjaone.backendinterviewproject.core.enums.CatalogEnum;
import com.ninjaone.backendinterviewproject.shop.transaction.entity.TransactionEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

@Service
public class PurchaseUseCase {


    private final CustomerDeviceUseCase deviceUseCase;
    private final CustomerDeviceServiceUseCase serviceUseCase;
    private final TransactionUseCase transactionUseCase;

    public PurchaseUseCase(CustomerDeviceUseCase devicePurchase,
                           CustomerDeviceServiceUseCase servicePurchase,
                           TransactionUseCase transactionService) {
        this.deviceUseCase = devicePurchase;
        this.serviceUseCase = servicePurchase;
        this.transactionUseCase = transactionService;
    }

    @Transactional
    public void includeDevicesAndServices(PurchaseDTO purchase) throws Exception {
        UUID traceId = UUID.randomUUID();
        for (PurchaseItemsDTO item : purchase.getItems()) {
            CustomerDevicesEntity customerDevicesEntity = deviceUseCase.purchaseDeviceForCustomer(traceId, purchase.getCustomerId(), item.getDeviceId());
            transactionUseCase.insert(traceId, purchase.getCustomerId(), item.getDeviceId(), customerDevicesEntity.getDevice().getPrice(), CatalogEnum.DEVICE);
            for (String service : item.getServices()) {
                CustomerDeviceServicesEntity customerDeviceServicesEntity = serviceUseCase.purchaseServiceForDevice(traceId, customerDevicesEntity, service);
                transactionUseCase.insert(traceId, purchase.getCustomerId(), service, customerDeviceServicesEntity.getHiredService().getPrice(), CatalogEnum.SERVICE);
            }
        }
    }

    public void filterReportsByMonth(int year, int month) throws Exception {
        Set<TransactionEntity> transactionEntities = transactionUseCase.filterByMonth(year, month);
        DoubleStream totalPaid = transactionEntities.stream().mapToDouble(transaction -> transaction.getPrice());

    }
}
