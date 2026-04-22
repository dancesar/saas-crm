package com.saas.crm.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saas.crm.application.CreateLeadUseCase;
import com.saas.crm.application.GetLeadByIdUseCase;
import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.application.ListLeadsUseCase;
import com.saas.crm.domain.Lead;
import com.saas.crm.infrastructure.dto.CreateLeadRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @MockitoBean
    private CreateLeadUseCase createLeadUseCase;

    @MockitoBean
    private ListLeadsUseCase listLeadsUseCase;

    @MockitoBean
    private GetLeadByIdUseCase getLeadByIdUseCase;

    @Test
    void shouldCreateLeadSuccessfully() throws Exception {

        CreateLeadRequestDTO request = new CreateLeadRequestDTO();
        request.setId(1L);
        request.setName("Danillo");
        request.setEmail("danillo@email.com");
        request.setPhone("11999999999");

        when(createLeadUseCase.execute(any(Lead.class)))
                .thenReturn(new Lead(1L, "Danillo", "danillo@email.com", "11999999999"));

        mockMvc.perform(post("/leads")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Danillo"))
                .andExpect(jsonPath("$.email").value("danillo@email.com"));
    }

    @Test
    void shouldReturnLeadById() throws Exception {

        when(getLeadByIdUseCase.execute(1L))
                .thenReturn(new Lead(1L, "Danillo", "danillo@email.com", "11999999999"));

        mockMvc.perform(get("/leads/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Danillo"))
                .andExpect(jsonPath("$.email").value("danillo@email.com"));
    }
}
