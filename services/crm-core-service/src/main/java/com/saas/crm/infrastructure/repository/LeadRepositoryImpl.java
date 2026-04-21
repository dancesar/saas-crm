package com.saas.crm.infrastructure.repository;

import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.domain.Lead;
import com.saas.crm.infrastructure.entity.LeadEntity;
import com.saas.crm.infrastructure.repository.jpa.LeadJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeadRepositoryImpl implements LeadRepositoryPort{

    private final LeadJpaRepository jpaRepository;

    public LeadRepositoryImpl(LeadJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Lead save(Lead lead) {

        LeadEntity entity = new LeadEntity(
                lead.getName(),
                lead.getEmail(),
                lead.getPhone()
        );

        LeadEntity saved = jpaRepository.save(entity);

        return new Lead(
                saved.getName(),
                saved.getEmail(),
                saved.getPhone()
        );
    }

    @Override
    public List<Lead> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(entity -> new Lead(
                        entity.getName(),
                        entity.getEmail(),
                        entity.getPhone()
                ))
                .toList();
    }
}
