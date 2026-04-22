package com.saas.crm.application.port;

import com.saas.crm.domain.Lead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListLeadsUseCase {
    private static final Logger log = LoggerFactory.getLogger(ListLeadsUseCase.class);

    private final LeadRepositoryPort repository;

    public ListLeadsUseCase(LeadRepositoryPort repository) {
        this.repository = repository;
    }

    public List<Lead> execute() {

        log.info("action=list_leads status=start");

        List<Lead> leads = repository.findAll();

        log.info("action=list_leads status=success total={}", leads.size());

        return leads;
    }
}
