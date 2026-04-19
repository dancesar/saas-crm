package com.saas.crm.infrastructure.repository.jpa;

import com.saas.crm.infrastructure.entity.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadJpaRepository extends JpaRepository<LeadEntity, String> {
}
