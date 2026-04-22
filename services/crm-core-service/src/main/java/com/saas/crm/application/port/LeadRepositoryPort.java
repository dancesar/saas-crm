package com.saas.crm.application.port;

import com.saas.crm.domain.Lead;

public interface LeadRepositoryPort {

    Lead save(Lead lead);
}
