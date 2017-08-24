package pl.czd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProposalPositionTemplate {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "proposal_template_id")
    private ProposalTemplate proposalTemplate;
    private String header;
    private int type;
    private int maxFilesQuantity;
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List<AnswerTemplate> answers;
    private Integer positionOrder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPositionOrder() {
        return positionOrder;
    }

    public void setPositionOrder(int positionOrder) {
        this.positionOrder = positionOrder;
    }

    public ProposalTemplate getProposalTemplate() {
        return proposalTemplate;
    }

    public void setProposalTemplate(ProposalTemplate proposalTemplate) {
        this.proposalTemplate = proposalTemplate;
    }

    public List<AnswerTemplate> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerTemplate> answers) {
        this.answers = answers;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMaxFilesQuantity() {
        return maxFilesQuantity;
    }

    public void setMaxFilesQuantity(int maxFilesQuantity) {
        this.maxFilesQuantity = maxFilesQuantity;
    }

    public ProposalPositionTemplate addAnswer(AnswerTemplate answer) {
        if (answers == null)
            answers = new ArrayList<>();
        answers.add(answer);
        answer.setPosition(this);
        return this;
    }
}

