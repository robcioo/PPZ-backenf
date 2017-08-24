package pl.czd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String path;
    private String name;
    private String extension;
    private long size;
    private String description;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_position_id")
    private ProposalPosition proposalPosition;
    public long getId() {
        return id;
    }

    public File setId(long id) {
        this.id = id;
        return this;
    }

    public ProposalPosition getProposalPosition() {
        return proposalPosition;
    }

    public void setProposalPosition(ProposalPosition proposalPosition) {
        this.proposalPosition = proposalPosition;
    }

    public String getPath() {
        return path;
    }

    public File setPath(String path) {
        this.path = path;
        return this;
    }

    public String getName() {
        return name;
    }

    public File setName(String name) {
        this.name = name;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public File setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public long getSize() {
        return size;
    }

    public File setSize(long size) {
        this.size = size;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public File setDescription(String description) {
        this.description = description;
        return this;
    }
}
