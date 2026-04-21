package com.saas.crm.application;

import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.domain.Lead;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListLeadsUseCase {

    private final LeadRepositoryPort repository;

    public ListLeadsUseCase(LeadRepositoryPort repository) {
        this.repository = repository;
    }

    public List<Lead> execute() {
        return repository.findAll();
    }
}
