package com.saas.crm.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.infrastructure.dto.CreateLeadRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LeadController.class)
public class LeadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private LeadRepositoryPort leadRepositoryPort; // Mock do repositório para evitar dependências externas

    @Test
    void shouldCreateLeadSuccessfully() throws Exception {

        CreateLeadRequestDTO request = new CreateLeadRequestDTO();
        request.setName("Danillo");
        request.setEmail("danillo@email.com");
        request.setPhone("11999999999");

        // 👇 ESSA LINHA RESOLVE O PROBLEMA
        when(leadRepositoryPort.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(post("/leads")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Danillo"))
                .andExpect(jsonPath("$.email").value("danillo@email.com"));
    }
}
