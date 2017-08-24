package pl.czd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.czd.entities.Proposal;
import pl.czd.entities.ProposalPosition;
import pl.czd.entities.ProposalPositionTemplate;
import pl.czd.entities.ProposalTemplate;
import pl.czd.repositories.ProposalPositionRepository;
import pl.czd.repositories.ProposalRepository;
import pl.czd.repositories.ProposalTemplateRepository;

import java.util.List;

@RequestMapping("api")
@RestController
public class ProposalController {
    @Autowired
    ProposalTemplateRepository templateRepository;
    @Autowired
    ProposalRepository proposalRepository;
    @Autowired
    ProposalPositionRepository proposalPositionRepository;

    @GetMapping("templates")
    public List<ProposalTemplate> getProposalTemplates() {
        return templateRepository.findAll();
    }

    @PostMapping("proposal/{id}")
    public Proposal createProposalForTemplateById(@PathVariable String id) {//user
        ProposalTemplate template = templateRepository.findById(id);
        Proposal proposal = new Proposal(template);
        proposalRepository.save(proposal);
        return proposal;
    }
    @GetMapping("proposal/{id}")
    public Proposal getProposalById(@PathVariable String id) {
        Proposal proposal = proposalRepository.findById(id);
        return proposal;
    }

    @PutMapping("position")
    public ProposalPosition updateProposalPosition(@RequestBody ProposalPosition proposalPosition) {
        ProposalPosition positionToUpdate = proposalPositionRepository.findById(proposalPosition.getId());
        positionToUpdate.updateFields(proposalPosition);
        proposalPositionRepository.save(positionToUpdate);
        return  positionToUpdate;
    }

    @GetMapping("proposals")
    public List<Proposal> findByUser(){
        return proposalRepository.findByUser(null);
    }

}
