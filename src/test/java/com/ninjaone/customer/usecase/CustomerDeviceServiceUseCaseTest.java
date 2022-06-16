package com.ninjaone.customer.usecase;

import com.ninjaone.catalog.domain.enums.CatalogCompatibilityEnum;
import com.ninjaone.catalog.infra.entity.DeviceEntity;
import com.ninjaone.catalog.infra.entity.ServiceEntity;
import com.ninjaone.catalog.infra.repository.ServiceForDeviceRepository;
import com.ninjaone.customer.infra.entity.CustomerDevicesEntity;
import com.ninjaone.customer.infra.repository.CustomerDeviceServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerDeviceServiceUseCaseTest {

    @InjectMocks
    private CustomerDeviceServiceUseCase useCase;

    @Mock
    private ServiceForDeviceRepository serviceForDeviceRepository;

    @Mock
    private CustomerDeviceServiceRepository customerDeviceServiceRepository;

    @Test
    public void shouldAllowCompatibleDeviceAndService() throws Exception {

        ServiceEntity serviceEntity = new ServiceEntity("quicktime-premium", 0.5d, CatalogCompatibilityEnum.IOS_SYSTEM.getValue());

        CustomerDevicesEntity customerDevicesEntity = new CustomerDevicesEntity();
        customerDevicesEntity.setDevice(new DeviceEntity("IOS-phone", 1d, CatalogCompatibilityEnum.IOS_SYSTEM.getValue()));

        when(serviceForDeviceRepository.findById(eq(serviceEntity.getItem()))).thenReturn(Optional.of(serviceEntity));

        useCase.purchaseServiceForDevice(customerDevicesEntity, serviceEntity.getItem());


        verify(serviceForDeviceRepository, times(1)).findById(eq(serviceEntity.getItem()));
        verify(customerDeviceServiceRepository, times(1)).save(any());
    }


    @Test
    public void shouldNotAllowCompatibleDeviceAndService() throws Exception {

        ServiceEntity serviceEntity = new ServiceEntity("xbox-app", 0.5d, CatalogCompatibilityEnum.WINDOWS_SYSTEM.getValue());

        CustomerDevicesEntity customerDevicesEntity = new CustomerDevicesEntity();
        customerDevicesEntity.setDevice(new DeviceEntity("IOS-phone", 1d, CatalogCompatibilityEnum.IOS_SYSTEM.getValue()));

        when(serviceForDeviceRepository.findById(eq(serviceEntity.getItem()))).thenReturn(Optional.of(serviceEntity));

        Exception exception = assertThrows(Exception.class,
                () -> useCase.purchaseServiceForDevice(customerDevicesEntity, serviceEntity.getItem()));


        Assertions.assertEquals(exception.getMessage(), "service is not compatible for this device");
        verify(serviceForDeviceRepository, times(1)).findById(eq(serviceEntity.getItem()));
        verify(customerDeviceServiceRepository, times(0)).save(any());
    }


    @Test
    public void shouldHandleNotExistentService() throws Exception {

        String serviceId = "any-service";
        CustomerDevicesEntity customerDevicesEntity = new CustomerDevicesEntity();
        customerDevicesEntity.setDevice(new DeviceEntity("IOS-phone", 1d, CatalogCompatibilityEnum.IOS_SYSTEM.getValue()));

        when(serviceForDeviceRepository.findById(eq(serviceId))).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class,
                () -> useCase.purchaseServiceForDevice(customerDevicesEntity, serviceId));


        Assertions.assertEquals(exception.getMessage(), "service not found");
        verify(serviceForDeviceRepository, times(1)).findById(eq(serviceId));
        verify(customerDeviceServiceRepository, times(0)).save(any());
    }
}
