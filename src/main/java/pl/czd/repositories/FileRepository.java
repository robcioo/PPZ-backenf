package pl.czd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czd.entities.File;

public interface FileRepository extends JpaRepository<File,Long>{
    public File findById(String id);
}
