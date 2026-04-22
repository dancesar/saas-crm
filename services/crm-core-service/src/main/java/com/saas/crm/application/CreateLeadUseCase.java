package com.saas.crm.application;

import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.domain.Lead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateLeadUseCase {

    private final LeadRepositoryPort leadRepository;

    private static final Logger log = LoggerFactory.getLogger(CreateLeadUseCase.class);

    public CreateLeadUseCase(LeadRepositoryPort leadRepository) {
        this.leadRepository = leadRepository;
    }

    public Lead execute(Lead lead) {

        log.info("action=create_lead status=start email={}", lead.getEmail());

        Lead saved = leadRepository.save(lead);

        log.info("action=create_lead status=success email={}", lead.getEmail());

        return saved;
    }
}
