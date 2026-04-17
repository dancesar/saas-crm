package com.saas.crm.infrastructure;

import com.saas.crm.application.CreateLeadUseCase;
import com.saas.crm.domain.Lead;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leads")
public class LeadController {

    private final CreateLeadUseCase useCase = new CreateLeadUseCase();

    @PostMapping
    public Lead create(@RequestBody Lead lead) {
        return useCase.execute(lead);
    }
}
