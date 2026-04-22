package com.saas.crm.infrastructure.dto;

import lombok.Data;

@Data
public class CreateLeadRequestDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
}
