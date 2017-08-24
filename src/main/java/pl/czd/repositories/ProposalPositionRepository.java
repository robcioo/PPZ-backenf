package pl.czd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czd.entities.ProposalPosition;

public interface ProposalPositionRepository extends JpaRepository<ProposalPosition,Long> {
    public ProposalPosition findById(String id);
}
