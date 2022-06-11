package com.ninjaone.backendinterviewproject;

import com.ninjaone.backendinterviewproject.dto.DeviceDTO;
import com.ninjaone.backendinterviewproject.enums.Compatibility;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.ServiceForDeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BackendInterviewProjectApplicationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ServiceForDeviceService serviceForDeviceService;

    @Test
    public void createDependencies(){
        DeviceDTO device = new DeviceDTO();
        device.setItem("windows");
        device.setPrice(4d);
        device.setCompatibility(Compatibility.WINDOWS_SYSTEM);
        device.setAvailable(true);
        deviceService.save(device);
        //serviceForDeviceService.insert();
    }


}
