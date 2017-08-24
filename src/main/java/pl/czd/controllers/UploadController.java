package pl.czd.controllers;

import com.sun.deploy.nativesandbox.comm.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.czd.entities.Proposal;
import pl.czd.entities.ProposalPosition;
import pl.czd.repositories.ProposalPositionRepository;
import pl.czd.services.FileService;

import java.util.List;

@RestController
@RequestMapping("api")
public class UploadController {
    @Autowired
    private FileService fileService;
    @Autowired
    private ProposalPositionRepository proposalPositionRepository;

    @PostMapping("uploaddata")
    public ResponseEntity<String> uploadDataFile(
            @RequestParam List<MultipartFile> files, @RequestParam String proposalId) {
        ProposalPosition proposalPosition = proposalPositionRepository.findById(proposalId);
        if(proposalPosition==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        for (MultipartFile file : files) {
            if (!fileService.saveFile(file, proposalPosition))
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        proposalPositionRepository.save(proposalPosition);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}