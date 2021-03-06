package com.ninjaone.catalog.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.catalog.domain.dto.CatalogDTO;
import com.ninjaone.catalog.domain.enums.CatalogCompatibilityEnum;
import com.ninjaone.catalog.usecase.DeviceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        CatalogDTO dto = new CatalogDTO();
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

        CatalogDTO dto = new CatalogDTO();
        dto.setItem("windows");
        dto.setCompatibility("unkown-compatibility");
        dto.setPrice(1d);

        mockMvc.perform(post("/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotAcceptZeroPrice() throws Exception {

        CatalogDTO dto = new CatalogDTO();
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

        CatalogDTO dto = new CatalogDTO();
        dto.setCompatibility(CatalogCompatibilityEnum.WINDOWS_SYSTEM.getValue());
        dto.setPrice(10d);

        mockMvc.perform(post("/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldRemoveItem() throws Exception {



        mockMvc.perform(delete("/devices/{id}", "windows")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldUpdateItem() throws Exception {

        CatalogDTO dto = new CatalogDTO();
        dto.setPrice(10d);
        dto.setCompatibility("ios_system");

        mockMvc.perform(put("/devices/{id}", "mac")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetItem() throws Exception {

        CatalogDTO dto = new CatalogDTO();
        dto.setItem("phone");
        dto.setPrice(10d);
        dto.setCompatibility("ios_system");

        when(useCase.get(eq(dto.getItem()))).thenReturn(dto);

        ResultActions response = mockMvc.perform(get("/devices/{id}", "phone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.item").value("phone"))
                .andExpect(jsonPath("$.price").value(10d))
                .andExpect(jsonPath("$.available").value(true))
                .andExpect(jsonPath("$.compatibility").value("ios_system"));

    }
}
