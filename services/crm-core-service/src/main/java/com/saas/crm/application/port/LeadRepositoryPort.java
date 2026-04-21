package com.saas.crm.application.port;

import com.saas.crm.domain.Lead;

import java.util.Optional;

public interface LeadRepositoryPort {

    Lead save(Lead lead);

    Optional<Lead> findById(Long id);
}
