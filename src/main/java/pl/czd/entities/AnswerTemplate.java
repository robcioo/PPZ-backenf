package pl.czd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class AnswerTemplate {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String answer;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "position_id")
    private ProposalPositionTemplate position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public AnswerTemplate setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public ProposalPositionTemplate getPosition() {
        return position;
    }

    public AnswerTemplate setPosition(ProposalPositionTemplate position) {
        this.position = position;
        return this;
    }
}
