package pl.czd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czd.entities.Proposal;
import pl.czd.entities.User;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal,Long> {
    List<Proposal> findByUser(User user);
    public Proposal findById(String id);
}
