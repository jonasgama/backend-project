package com.ninjaone.core.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.catalog.domain.dto.CatalogDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldCatchGlobalExceptionHandler() throws Exception {
        CatalogDTO dto = new CatalogDTO();
        dto.setCompatibility("windows_system");
        dto.setPrice(20d);

        mockMvc.perform(post("/services")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("item - catalog id not provided"));
    }
}
