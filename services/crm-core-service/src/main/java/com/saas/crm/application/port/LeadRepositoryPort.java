package com.saas.crm.application.port;

import com.saas.crm.domain.Lead;

import java.util.List;

public interface LeadRepositoryPort {

    Lead save(Lead lead);

    List<Lead> findAll();
}
