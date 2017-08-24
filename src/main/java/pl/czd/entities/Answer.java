package pl.czd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String answer;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "position_id")
    private ProposalPosition position;
    public Answer(){}
    public Answer(AnswerTemplate answerTemplate) {
        this.answer=answerTemplate.getAnswer();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public Answer setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public ProposalPosition getPosition() {
        return position;
    }

    public Answer setPosition(ProposalPosition position) {
        this.position = position;return this;
    }
}
