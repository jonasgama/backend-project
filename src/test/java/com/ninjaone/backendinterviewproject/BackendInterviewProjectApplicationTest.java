package com.ninjaone.backendinterviewproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.database.ServiceForDeviceRepository;
import com.ninjaone.backendinterviewproject.dto.DeviceDTO;
import com.ninjaone.backendinterviewproject.dto.PurchaseDTO;
import com.ninjaone.backendinterviewproject.dto.PurchaseItemsDTO;
import com.ninjaone.backendinterviewproject.dto.ServiceDTO;
import com.ninjaone.backendinterviewproject.entity.CustomerEntity;
import com.ninjaone.backendinterviewproject.entity.ServiceEntity;
import com.ninjaone.backendinterviewproject.entity.TransactionsEntity;
import com.ninjaone.backendinterviewproject.enums.Compatibility;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.ServiceForDeviceService;
import com.ninjaone.backendinterviewproject.service.TransactionService;
import org.assertj.core.util.Sets;
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
    private DeviceService deviceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceForDeviceRepository serviceForDeviceRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void shouldCalculateMonthlyCost() throws Exception {
        this.createDependencies();

        PurchaseDTO purchase = new PurchaseDTO();
        purchase.setCustomerId("retailer-test");

        PurchaseItemsDTO windowsSet = new PurchaseItemsDTO();
        windowsSet.setDeviceId("windows");
        //windowsSet.addServices(Set.of("windows"));

        PurchaseItemsDTO macSet = new PurchaseItemsDTO();
        macSet.setDeviceId("mac");
        //purchase.setDeviceId("windows");

        purchase.addItems(windowsSet, macSet);

        mockMvc.perform(post("/purchase/devices")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(purchase)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Set<TransactionsEntity> transactionsEntities = transactionService.filterByMonth(2022, 06);
        transactionsEntities.size();
    }

    public void createDependencies(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("retailer-test");
        customerRepository.save(customerEntity);


        DeviceDTO windowsDevice = new DeviceDTO();
        windowsDevice.setItem("windows");
        windowsDevice.setPrice(4d);
        windowsDevice.setCompatibility(Compatibility.WINDOWS_SYSTEM);
        windowsDevice.setAvailable(true);

        DeviceDTO macDevice = new DeviceDTO();
        macDevice.setItem("mac");
        macDevice.setPrice(4d);
        macDevice.setCompatibility(Compatibility.IOS_SYSTEM);
        macDevice.setAvailable(true);

        deviceRepository.save(windowsDevice);
        deviceRepository.save(macDevice);


        ServiceEntity antivirusForWindows = new ServiceEntity();
        antivirusForWindows.setAvailable(true);
        antivirusForWindows.setPrice(5d);
        antivirusForWindows.setCompatibility(Compatibility.WINDOWS_SYSTEM);
        antivirusForWindows.setItem("antivirus-for-windows");


        ServiceEntity antivirusForMac = new ServiceEntity();
        antivirusForMac.setAvailable(true);
        antivirusForMac.setPrice(7d);
        antivirusForMac.setCompatibility(Compatibility.IOS_SYSTEM);
        antivirusForMac.setItem("antivirus-for-mac");


        ServiceEntity backup = new ServiceEntity();
        backup.setAvailable(true);
        backup.setPrice(3d);
        backup.setCompatibility(Compatibility.GENERIC);
        backup.setItem("backup");

        ServiceEntity screenShare = new ServiceEntity();
        screenShare.setAvailable(true);
        screenShare.setPrice(1d);
        screenShare.setCompatibility(Compatibility.GENERIC);
        screenShare.setItem("screen-share");

        serviceForDeviceRepository.save(antivirusForWindows);
        serviceForDeviceRepository.save(antivirusForMac);
        serviceForDeviceRepository.save(backup);
    }


}
