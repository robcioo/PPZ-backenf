package pl.czd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.czd.controllers.UploadController;
import pl.czd.entities.Proposal;
import pl.czd.entities.ProposalPosition;
import pl.czd.entities.User;
import pl.czd.repositories.FileRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {
    public static final int FILENAME_LENGTH = 10;
    @Value("${blob.directory}")
    public String directory;
    @Autowired
    private FileRepository fileRepository;

    @Transactional
    public boolean saveFile(MultipartFile file,  ProposalPosition proposalPosition) {
        if (file != null) {
            File fileToSave;
            try {
                pl.czd.entities.File dbFile = new pl.czd.entities.File();
                dbFile.setName(file.getOriginalFilename()).setSize(file.getSize());
                fileToSave = new File(directory + getPathForFile(dbFile,  proposalPosition.getProposal()));
                fileToSave.getParentFile().mkdirs();
                fileToSave.createNewFile();
                FileOutputStream fos = new FileOutputStream(fileToSave);
                fos.write(file.getBytes());
                fos.close();
                dbFile.setPath(fileToSave.getPath());
                proposalPosition.addFile(dbFile);
                System.out.println("Your File Name is : " + fileToSave.getName());
            } catch (FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
            return true;
        } else {
            return false;
        }
    }

    private String getPathForFile(pl.czd.entities.File file,  Proposal proposal) {
        User user=proposal.getUser();
        String fileName = String.valueOf(file.getId()) ;
        StringBuilder sb = new StringBuilder();
        while (sb.length() + fileName.length() < FILENAME_LENGTH)
            sb.append('0');
        sb.append(fileName);
        return "/" +/* user.getLogin() + "/"*/  proposal.getId() + "/" + sb.toString();
    }
}
