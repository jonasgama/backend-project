package com.ninjaone.backendinterviewproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.customer.repository.CustomerRepository;
import com.ninjaone.backendinterviewproject.catalog.repository.ServiceForDeviceRepository;
import com.ninjaone.backendinterviewproject.core.dto.DeviceDTO;
import com.ninjaone.backendinterviewproject.core.dto.PurchaseDTO;
import com.ninjaone.backendinterviewproject.core.dto.PurchaseItemsDTO;
import com.ninjaone.backendinterviewproject.customer.entity.CustomerEntity;
import com.ninjaone.backendinterviewproject.catalog.entity.ServiceEntity;
import com.ninjaone.backendinterviewproject.shop.transaction.entity.TransactionEntity;
import com.ninjaone.backendinterviewproject.core.enums.CompatibilityEnum;
import com.ninjaone.backendinterviewproject.catalog.usecase.DeviceUseCase;
import com.ninjaone.backendinterviewproject.shop.transaction.usecase.TransactionUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BackendInterviewProjectApplicationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeviceUseCase deviceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceForDeviceRepository serviceForDeviceRepository;

    @Autowired
    private TransactionUseCase transactionUseCase;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void shouldCalculateMonthlyCost() throws Exception {
        this.createDependencies();

        PurchaseDTO purchase = new PurchaseDTO();
        purchase.setCustomerId("retailer-test");

        String windowsDevice = "windows";
        Set<String> windowsServices = Set.of("antivirus-for-windows", "backup", "screen-share");
        purchase.addItems(createSetOfItems(windowsDevice, windowsServices));
        purchase.addItems(createSetOfItems(windowsDevice, windowsServices));
        purchase.addItems(createSetOfItems(windowsDevice, windowsServices));

        String macDevice = "mac";
        Set<String> macServices = Set.of("antivirus-for-mac", "backup", "screen-share");
        purchase.addItems(createSetOfItems(macDevice, macServices));
        purchase.addItems(createSetOfItems(macDevice, macServices));
        purchase.addItems(createSetOfItems(macDevice, macServices));


        mockMvc.perform(post("/purchase/devices")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(purchase)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Set<TransactionEntity> transactionsEntities = transactionUseCase.filterByMonth(2022, 06);
        Assertions.assertThat(transactionsEntities.size()).isEqualTo(24);
    }

    private PurchaseItemsDTO createSetOfItems(String device, Set<String> services) {
        PurchaseItemsDTO windowsSet = new PurchaseItemsDTO();
        windowsSet.setDeviceId(device);
        windowsSet.addServices(services);
        return windowsSet;
    }

    public void createDependencies() throws Exception {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("retailer-test");
        customerRepository.save(customerEntity);


        DeviceDTO windowsDevice = new DeviceDTO();
        windowsDevice.setItem("windows");
        windowsDevice.setPrice(4d);
        windowsDevice.setCompatibility(CompatibilityEnum.WINDOWS_SYSTEM);
        windowsDevice.setAvailable(true);

        DeviceDTO macDevice = new DeviceDTO();
        macDevice.setItem("mac");
        macDevice.setPrice(4d);
        macDevice.setCompatibility(CompatibilityEnum.IOS_SYSTEM);
        macDevice.setAvailable(true);

        deviceRepository.insert(windowsDevice);
        deviceRepository.insert(macDevice);


        ServiceEntity antivirusForWindows = new ServiceEntity();
        antivirusForWindows.setAvailable(true);
        antivirusForWindows.setPrice(5d);
        antivirusForWindows.setCompatibility(CompatibilityEnum.WINDOWS_SYSTEM);
        antivirusForWindows.setItem("antivirus-for-windows");


        ServiceEntity antivirusForMac = new ServiceEntity();
        antivirusForMac.setAvailable(true);
        antivirusForMac.setPrice(7d);
        antivirusForMac.setCompatibility(CompatibilityEnum.IOS_SYSTEM);
        antivirusForMac.setItem("antivirus-for-mac");


        ServiceEntity backup = new ServiceEntity();
        backup.setAvailable(true);
        backup.setPrice(3d);
        backup.setCompatibility(CompatibilityEnum.GENERIC);
        backup.setItem("backup");

        ServiceEntity screenShare = new ServiceEntity();
        screenShare.setAvailable(true);
        screenShare.setPrice(1d);
        screenShare.setCompatibility(CompatibilityEnum.GENERIC);
        screenShare.setItem("screen-share");

        serviceForDeviceRepository.save(antivirusForWindows);
        serviceForDeviceRepository.save(antivirusForMac);
        serviceForDeviceRepository.save(backup);
        serviceForDeviceRepository.save(screenShare);
    }


}
