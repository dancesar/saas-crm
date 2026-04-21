package com.saas.crm.application;

import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.domain.Lead;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateLeadUseCaseTest {

    @Test
    void shouldSaveLeadUsingRepository() {
        // 1. Setup (Mocking)
        LeadRepositoryPort fakeRepository = mock(LeadRepositoryPort.class);

        // Criamos o objeto uma única vez
        Lead lead = new Lead("Danillo", "danillo@email.com", "11999999999");

        // Configuramos o mock: quando o save for chamado, retorna o objeto que criamos
        when(fakeRepository.save(any(Lead.class))).thenReturn(lead);

        // 2. Execution
        CreateLeadUseCase useCase = new CreateLeadUseCase(fakeRepository);
        Lead result = useCase.execute(lead);

        // 3. Assertions
        assertNotNull(result);
        assertEquals("Danillo", result.getName());

        // Dica de Arquiteto: Verifique se o repository foi realmente chamado
        verify(fakeRepository, times(1)).save(any(Lead.class));
    }
}
