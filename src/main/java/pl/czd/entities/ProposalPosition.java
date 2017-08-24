package pl.czd.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProposalPosition {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;
    private int type;
    private String header;
    private String text;
    private String description;
    private int maxFilesQuantity;
    private Integer positionOrder;
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List<Answer> answers;
    @OneToMany(mappedBy = "proposalPosition", cascade = CascadeType.ALL)
    private List<File> files;
    private String selectedId;
    public ProposalPosition(){}
    public ProposalPosition(ProposalPositionTemplate positionTemplate) {
        type=positionTemplate.getType();
        header=positionTemplate.getHeader();
        maxFilesQuantity=positionTemplate.getMaxFilesQuantity();
        positionOrder=positionTemplate.getPositionOrder();
        if(positionTemplate.getAnswers()!=null)
            for(AnswerTemplate answerTemplate:positionTemplate.getAnswers()){
                addAnswer(new Answer(answerTemplate));
            }
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public int getPositionOrder() {
        return positionOrder;
    }

    public void setPositionOrder(int positionOrder) {
        this.positionOrder = positionOrder;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMaxFilesQuantity() {
        return maxFilesQuantity;
    }

    public void setMaxFilesQuantity(int maxFilesQuantity) {
        this.maxFilesQuantity = maxFilesQuantity;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPositionOrder(Integer positionOrder) {
        this.positionOrder = positionOrder;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public ProposalPosition addAnswer(Answer answer) {
        if (answers == null)
            answers = new ArrayList<>();
        answers.add(answer);
        answer.setPosition(this);
        return this;
    }

    public void updateFields(ProposalPosition proposalPosition) {
        description=proposalPosition.description;
        text=proposalPosition.text;
        selectedId=proposalPosition.selectedId;
    }

    public void addFile(File dbFile) {
        if(files==null){
            files=new ArrayList<>();
        }
        files.add(dbFile);
        dbFile.setProposalPosition(this);
    }
}
