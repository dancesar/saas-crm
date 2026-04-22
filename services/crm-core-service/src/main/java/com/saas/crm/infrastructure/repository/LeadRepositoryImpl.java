package com.saas.crm.infrastructure.repository;

import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.domain.Lead;
import com.saas.crm.infrastructure.entity.LeadEntity;
import com.saas.crm.infrastructure.repository.jpa.LeadJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                saved.getId(),
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
                        entity.getId(),
                        entity.getName(),
                        entity.getEmail(),
                        entity.getPhone()
                ))
                .toList();
    }

    @Override
    public Optional<Lead> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entity -> new Lead(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone()));
    }

}
