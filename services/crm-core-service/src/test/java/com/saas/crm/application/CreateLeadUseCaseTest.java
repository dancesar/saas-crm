package com.saas.crm.application;

import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.domain.Lead;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateLeadUseCaseTest {

    @Test
    void shouldSaveLeadUsingRepository() {

        // fake repository (simula banco)
        LeadRepositoryPort fakeRepository = lead -> lead;

        CreateLeadUseCase useCase = new CreateLeadUseCase(fakeRepository);

        Lead lead = new Lead("Danillo", "danillo@email.com", "11999999999");

        Lead result = useCase.execute(lead);

        assertNotNull(result);
        assertEquals("Danillo", result.getName());
    }
}
