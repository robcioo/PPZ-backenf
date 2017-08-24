package pl.czd.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Proposal {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private ProposalState state;
    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
    private List<ProposalPosition> positions;

    public Proposal() {
    }

    public Proposal(ProposalTemplate template) {
        this.name = template.getName();
        this.state=ProposalState.NEW;
        if (template.getPositions() != null)
            for (ProposalPositionTemplate positionTemplate : template.getPositions()) {
                addPosition(new ProposalPosition(positionTemplate));
            }

    }

    public ProposalState getState() {
        return state;
    }

    public void setState(ProposalState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPositions(List<ProposalPosition> positions) {
        this.positions = positions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProposalPosition> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<ProposalPosition> positions) {
        this.positions = positions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addPosition(ProposalPosition position) {
        if (this.positions == null)
            positions = new ArrayList<>();
        this.positions.add(position);
        position.setProposal(this);
    }

}
