package com.ninjaone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.catalog.domain.dto.CatalogDTO;
import com.ninjaone.catalog.usecase.DeviceUseCase;
import com.ninjaone.order.domain.dto.OrderTransactionDTO;
import com.ninjaone.customer.infra.repository.CustomerRepository;
import com.ninjaone.catalog.infra.repository.ServiceForDeviceRepository;
import com.ninjaone.order.domain.dto.OrderDTO;
import com.ninjaone.order.domain.dto.OrderItemsDTO;
import com.ninjaone.customer.infra.entity.CustomerEntity;
import com.ninjaone.catalog.infra.entity.ServiceEntity;
import com.ninjaone.catalog.domain.enums.CatalogCompatibilityEnum;
import com.ninjaone.order.usecase.OrderTransactionUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    private OrderTransactionUseCase transactionUseCase;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void shouldCalculateMonthlyCost() throws Exception {
        this.createDependencies();

        OrderDTO purchase = new OrderDTO();
        purchase.setCustomerId("retailer-test");

        String windowsDevice = "windows";
        Set<String> windowsServices = Set.of("antivirus-for-windows", "backup", "screen-share");
        purchase.addItems(createSetOfItems(windowsDevice, windowsServices));
        purchase.addItems(createSetOfItems(windowsDevice, windowsServices));

        String macDevice = "mac";
        Set<String> macServices = Set.of("antivirus-for-mac", "backup", "screen-share");
        purchase.addItems(createSetOfItems(macDevice, macServices));
        purchase.addItems(createSetOfItems(macDevice, macServices));
        purchase.addItems(createSetOfItems(macDevice, macServices));


        mockMvc.perform(post("/orders/devices")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(purchase)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        ResultActions resultActions = mockMvc.perform(get("/orders/transactions/{customerId}", "retailer-test")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        OrderTransactionDTO response = objectMapper.readValue(contentAsString, OrderTransactionDTO.class);


        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getYear()).isEqualTo(LocalDate.now().getYear());
        Assertions.assertThat(response.getMonth()).isEqualTo(LocalDate.now().getMonthValue());
        Assertions.assertThat(response.getTotalPaid()).isEqualTo(71);
        Assertions.assertThat(response.getItems().size()).isEqualTo(20);
        Assertions.assertThat(response.getSummary().get("backup")).isEqualTo(15);
        Assertions.assertThat(response.getSummary().get("antivirus-for-mac")).isEqualTo(21);
        Assertions.assertThat(response.getSummary().get("screen-share")).isEqualTo(5);
        Assertions.assertThat(response.getSummary().get("antivirus-for-windows")).isEqualTo(10);
        Assertions.assertThat(response.getSummary().get("windows")).isEqualTo(8);
        Assertions.assertThat(response.getSummary().get("mac")).isEqualTo(12);

    }

    private OrderItemsDTO createSetOfItems(String device, Set<String> services) {
        OrderItemsDTO windowsSet = new OrderItemsDTO();
        windowsSet.setDeviceId(device);
        windowsSet.addServices(services);
        return windowsSet;
    }

    public void createDependencies() throws Exception {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("retailer-test");
        customerRepository.save(customerEntity);


        CatalogDTO windowsDevice = new CatalogDTO();
        windowsDevice.setItem("windows");
        windowsDevice.setPrice(4d);
        windowsDevice.setCompatibility(CatalogCompatibilityEnum.WINDOWS_SYSTEM.getValue());
        windowsDevice.setAvailable(true);

        CatalogDTO macDevice = new CatalogDTO();
        macDevice.setItem("mac");
        macDevice.setPrice(4d);
        macDevice.setCompatibility(CatalogCompatibilityEnum.IOS_SYSTEM.getValue());
        macDevice.setAvailable(true);

        deviceRepository.insert(windowsDevice);
        deviceRepository.insert(macDevice);


        ServiceEntity antivirusForWindows = new ServiceEntity();
        antivirusForWindows.setAvailable(true);
        antivirusForWindows.setPrice(5d);
        antivirusForWindows.setCompatibility(CatalogCompatibilityEnum.WINDOWS_SYSTEM);
        antivirusForWindows.setItem("antivirus-for-windows");


        ServiceEntity antivirusForMac = new ServiceEntity();
        antivirusForMac.setAvailable(true);
        antivirusForMac.setPrice(7d);
        antivirusForMac.setCompatibility(CatalogCompatibilityEnum.IOS_SYSTEM);
        antivirusForMac.setItem("antivirus-for-mac");


        ServiceEntity backup = new ServiceEntity();
        backup.setAvailable(true);
        backup.setPrice(3d);
        backup.setCompatibility(CatalogCompatibilityEnum.GENERIC);
        backup.setItem("backup");

        ServiceEntity screenShare = new ServiceEntity();
        screenShare.setAvailable(true);
        screenShare.setPrice(1d);
        screenShare.setCompatibility(CatalogCompatibilityEnum.GENERIC);
        screenShare.setItem("screen-share");

        serviceForDeviceRepository.save(antivirusForWindows);
        serviceForDeviceRepository.save(antivirusForMac);
        serviceForDeviceRepository.save(backup);
        serviceForDeviceRepository.save(screenShare);
    }


}
