package com.saas.crm.infrastructure;

import com.saas.crm.application.CreateLeadUseCase;
import com.saas.crm.application.GetLeadByIdUseCase;
import com.saas.crm.application.port.LeadRepositoryPort;
import com.saas.crm.application.port.ListLeadsUseCase;
import com.saas.crm.domain.Lead;
import com.saas.crm.infrastructure.dto.CreateLeadRequestDTO;
import com.saas.crm.infrastructure.dto.LeadResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Leads", description = "Operações relacionadas a leads")
@RestController
@RequestMapping("/leads")
public class LeadController {

    private static final Logger log = LoggerFactory.getLogger(LeadController.class);

    private final CreateLeadUseCase useCase;

    private final GetLeadByIdUseCase getLeadByIdUseCase;

    private final ListLeadsUseCase listLeadsUseCase;

    public LeadController(CreateLeadUseCase createLeadUseCase,
                          GetLeadByIdUseCase getLeadByIdUseCase, ListLeadsUseCase listLeadsUseCase) {

        this.useCase = createLeadUseCase;
        this.getLeadByIdUseCase = getLeadByIdUseCase;
        this.listLeadsUseCase = listLeadsUseCase;
    }

    @Operation(summary = "Criar um novo lead")
    @PostMapping
    public ResponseEntity<LeadResponseDTO> create(@RequestBody CreateLeadRequestDTO request) {

        log.info("action=http_create_lead status=received email={}", request.getEmail());

        Lead lead = new Lead(
                request.getId(),
                request.getName(),
                request.getEmail(),
                request.getPhone()
        );

        Lead saved = useCase.execute(lead);

        LeadResponseDTO response = new LeadResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone()
        );

        // 👇 adicionando links
        response.add(linkTo(methodOn(LeadController.class).create(request)).withSelfRel());
        response.add(linkTo(methodOn(LeadController.class).list()).withRel("all-leads"));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<LeadResponseDTO>> list() {

        List<Lead> leads = listLeadsUseCase.execute();

        List<LeadResponseDTO> response = leads.stream()
                .map(lead -> new LeadResponseDTO(
                        lead.getId(),
                        lead.getName(),
                        lead.getEmail(),
                        lead.getPhone()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadResponseDTO> getById(@PathVariable Long id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        Lead lead = getLeadByIdUseCase.execute(id);

        LeadResponseDTO response = new LeadResponseDTO(
                lead.getId(),
                lead.getName(),
                lead.getEmail(),
                lead.getPhone()
        );

        return ResponseEntity.ok(response);
    }
}
