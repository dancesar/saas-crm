package com.saas.crm.application;

import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.domain.Lead;

public class CreateLeadUseCase {

    private final LeadRepositoryPort leadRepository;

    public CreateLeadUseCase(LeadRepositoryPort leadRepository) {
        this.leadRepository = leadRepository;
    }

    public Lead execute(Lead lead) {
        return leadRepository.save(lead);
    }
}
