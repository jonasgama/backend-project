package com.ninjaone.catalog.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.catalog.domain.dto.CatalogDTO;
import com.ninjaone.catalog.domain.enums.CatalogCompatibilityEnum;
import com.ninjaone.catalog.usecase.ServiceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ServiceForDeviceControllerTest {


    @Mock
    private ServiceUseCase useCase;

    @InjectMocks
    private ServiceForDeviceController controller;

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

        mockMvc.perform(post("/services")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldRemoveItem() throws Exception {

        mockMvc.perform(delete("/services/{id}", "screen-share")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
