package pl.czd.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProposalTemplate {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;

    @OneToMany(mappedBy = "proposalTemplate", cascade = CascadeType.ALL)
    private List<ProposalPositionTemplate> positions;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProposalPositionTemplate> getPositions() {
        return positions;
    }

    public void setPositions(List<ProposalPositionTemplate> positions) {
        this.positions = positions;
    }

    public void addPosition(ProposalPositionTemplate position) {
        if (this.positions == null)
            positions = new ArrayList<>();
        this.positions.add(position);
        position.setProposalTemplate(this);
    }
}
