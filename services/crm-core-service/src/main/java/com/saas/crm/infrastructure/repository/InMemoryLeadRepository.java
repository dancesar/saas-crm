package com.saas.crm.infrastructure.repository;

import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.domain.Lead;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class InMemoryLeadRepository implements LeadRepositoryPort {

    private final List<Lead> database = new  ArrayList<>();

    @Override
    public Lead save(Lead lead) {
        database.add(lead);
        return lead;
    }

    @Override
    public List<Lead> findAll() {
        return List.of();
    }
}
