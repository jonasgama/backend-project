package com.ninjaone.backendinterviewproject.catalog.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.catalog.domain.dto.DeviceDTO;
import com.ninjaone.backendinterviewproject.catalog.domain.enums.CatalogCompatibilityEnum;
import com.ninjaone.backendinterviewproject.catalog.usecase.DeviceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class DeviceControllerTest {



    @Mock
    private DeviceUseCase useCase;

    @InjectMocks
    private DeviceController controller;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldSaveItem() throws Exception {

        DeviceDTO dto = new DeviceDTO();
        dto.setItem("windows");
        dto.setCompatibility(CatalogCompatibilityEnum.WINDOWS_SYSTEM.getValue());
        dto.setPrice(22d);

        mockMvc.perform(post("/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldValidateCompatibility() throws Exception {

        DeviceDTO dto = new DeviceDTO();
        dto.setItem("windows");
        dto.setCompatibility("phone");
        dto.setPrice(1d);

        mockMvc.perform(post("/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotAcceptZeroPrice() throws Exception {

        DeviceDTO dto = new DeviceDTO();
        dto.setItem("windows");
        dto.setCompatibility(CatalogCompatibilityEnum.WINDOWS_SYSTEM.getValue());
        dto.setPrice(0d);

        mockMvc.perform(post("/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotAcceptEmtpyName() throws Exception {

        DeviceDTO dto = new DeviceDTO();
        dto.setCompatibility(CatalogCompatibilityEnum.WINDOWS_SYSTEM.getValue());
        dto.setPrice(10d);

        mockMvc.perform(post("/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
