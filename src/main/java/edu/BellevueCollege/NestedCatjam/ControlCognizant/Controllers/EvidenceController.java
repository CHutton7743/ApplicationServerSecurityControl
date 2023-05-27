package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Evidence;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.EvidenceNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.EvidenceRepository;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.common.EvidenceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class EvidenceController {
    @Autowired
    private EvidenceRepository evidenceRepository;

    @Transactional
    @GetMapping("/api/v1/evidence/get")
    public ResponseEntity<Object> getEvidence(@RequestBody EvidenceRequest request) {
        try {
            Evidence evidence = evidenceRepository.findByIdAndOrganizationID(request.getEvidenceId(), request.getOrganizationId());
            return ResponseEntity.ok().body(evidence);
        } catch (EvidenceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @GetMapping("/api/v1/evidence/get_all_by_organization")
    public ResponseEntity<Object> getAllEvidence(@RequestBody long organizationId) {
        try {
            List<Evidence> evidence = evidenceRepository.findAllByOrganizationID(organizationId);
            return ResponseEntity.ok().body(evidence);
        } catch (EvidenceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @PostMapping("/api/v1/evidence/save")
    public ResponseEntity<Evidence> saveEvidence(@RequestBody Evidence evidence) {
        try {
            evidenceRepository.save(evidence);
            return ResponseEntity.ok().body(evidence);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(evidence);
        }
    }
}
