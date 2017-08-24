package pl.czd.repositories;

        import org.springframework.data.jpa.repository.JpaRepository;
        import pl.czd.entities.ProposalTemplate;

public interface ProposalTemplateRepository  extends JpaRepository<ProposalTemplate,Long>{
    public ProposalTemplate findById(String id);
}
