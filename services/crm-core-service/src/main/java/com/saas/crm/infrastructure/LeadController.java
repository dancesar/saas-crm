package com.saas.crm.infrastructure;

import com.saas.crm.application.CreateLeadUseCase;
import com.saas.crm.domain.Lead;
import com.saas.crm.infrastructure.dto.CreateLeadRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leads")
public class LeadController {

    private final CreateLeadUseCase useCase = new CreateLeadUseCase();

    @PostMapping
    public Lead create(@RequestBody CreateLeadRequestDTO request) {

        Lead lead = new Lead(
                request.getName(),
                request.getEmail(),
                request.getPhone());

        return useCase.execute(lead);
    }
}
