package com.saas.crm.infrastructure.dto;

import org.springframework.hateoas.RepresentationModel;

public class LeadResponseDTO extends RepresentationModel<LeadResponseDTO>{
    private Long id;
    private String name;
    private String email;
    private String phone;

    public LeadResponseDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
