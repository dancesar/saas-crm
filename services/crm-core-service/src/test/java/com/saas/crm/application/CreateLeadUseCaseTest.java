package com.saas.crm.application;

import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.domain.Lead;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateLeadUseCaseTest {

    @Test
    void shouldSaveLeadUsingRepository() {
        // 1. Cria o mock da porta
        LeadRepositoryPort fakeRepository = mock(LeadRepositoryPort.class);

        // 2. Configura o comportamento (quando salvar, retorne o próprio lead)
        Lead lead = new Lead("Danillo", "danillo@email.com", "11999999999");
        when(fakeRepository.save(any(Lead.class))).thenReturn(lead);

        // 3. Instancia o Use Case com o mock
        CreateLeadUseCase useCase = new CreateLeadUseCase(fakeRepository);

        Lead result = useCase.execute(lead);

        assertNotNull(result);
        assertEquals("Danillo", result.getName());

        // Verifica se o método save foi realmente chamado (Arquitetura Sênior)
        verify(fakeRepository, times(1)).save(lead);
    }
}
