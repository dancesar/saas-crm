package com.saas.crm.application;

import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.domain.Lead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetLeadByIdUseCase {

    private final LeadRepositoryPort repository;

    private static final Logger log = LoggerFactory.getLogger(GetLeadByIdUseCase.class);

    public GetLeadByIdUseCase(LeadRepositoryPort repository) {
        this.repository = repository;
    }

    public Lead execute(Long id) {
        log.info("action=get_lead_by_id status=start id={}", id);

        return repository.findById(id)
                .map(lead -> {
                    log.info("action=get_lead_by_id status=success id={}", id);
                    return lead;
                })
                .orElseThrow(() -> {
                    log.warn("action=get_lead_by_id status=not_found id={}", id);
                    return new RuntimeException("Lead not found");
            });
    }
}
