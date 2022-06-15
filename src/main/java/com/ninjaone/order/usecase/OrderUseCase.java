package com.ninjaone.order.usecase;

import com.ninjaone.order.domain.dto.OrderTransactionDTO;
import com.ninjaone.customer.usecase.CustomerDeviceUseCase;
import com.ninjaone.customer.usecase.CustomerDeviceServiceUseCase;
import com.ninjaone.order.domain.dto.OrderDTO;
import com.ninjaone.order.domain.dto.OrderItemsDTO;
import com.ninjaone.customer.infra.entity.CustomerDeviceServicesEntity;
import com.ninjaone.customer.infra.entity.CustomerDevicesEntity;
import com.ninjaone.catalog.domain.enums.CatalogEnum;
import com.ninjaone.order.infra.entity.OrderEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class OrderUseCase {


    private final CustomerDeviceUseCase deviceUseCase;
    private final CustomerDeviceServiceUseCase serviceUseCase;
    private final OrderTransactionUseCase transactionUseCase;

    public OrderUseCase(CustomerDeviceUseCase devicePurchase,
                        CustomerDeviceServiceUseCase servicePurchase,
                        OrderTransactionUseCase transactionService) {
        this.deviceUseCase = devicePurchase;
        this.serviceUseCase = servicePurchase;
        this.transactionUseCase = transactionService;
    }

    @Transactional
    public void includeDevicesAndServices(OrderDTO purchase) throws Exception {
        UUID traceId = UUID.randomUUID();
        for (OrderItemsDTO item : purchase.getItems()) {
            CustomerDevicesEntity customerDevicesEntity = deviceUseCase.purchaseDeviceForCustomer(traceId, purchase.getCustomerId(), item.getDeviceId());
            transactionUseCase.insert(traceId, purchase.getCustomerId(), item.getDeviceId(), customerDevicesEntity.getDevice().getPrice(), CatalogEnum.DEVICE);
            for (String service : item.getServices()) {
                CustomerDeviceServicesEntity customerDeviceServicesEntity = serviceUseCase.purchaseServiceForDevice(traceId, customerDevicesEntity, service);
                transactionUseCase.insert(traceId, purchase.getCustomerId(), service, customerDeviceServicesEntity.getHiredService().getPrice(), CatalogEnum.SERVICE);
            }
        }
    }

    public OrderTransactionDTO filterReportsByMonth(String customerId, int year, int month) throws Exception {
        OrderTransactionDTO transactions = new OrderTransactionDTO(year, month);

        Set<OrderEntity> transactionEntities = transactionUseCase.filterByMonth(customerId, year, month);

        transactions.parse(transactionEntities);
        return transactions;
    }
}
