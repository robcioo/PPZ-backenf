package pl.czd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import pl.czd.entities.*;
import pl.czd.repositories.ProposalTemplateRepository;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Autowired
    ProposalTemplateRepository proposalTemplateRepository;

    @PostConstruct
    public void init() {
        proposalTemplateRepository.deleteAll();
        ProposalTemplate proposalTemplate = new ProposalTemplate();
        proposalTemplate.setName("Przeszczep 1");
        ProposalPositionTemplate template = new ProposalPositionTemplate();
        template.setHeader("Desc1");
        template.setType(ProposalPositionType.DESCRIPTION.ordinal());
        template.setPositionOrder(5);
        proposalTemplate.addPosition(template);
        template.setProposalTemplate(proposalTemplate);
        template = new ProposalPositionTemplate();
        template.setPositionOrder(4);
        template.setHeader("File");
        template.setType(ProposalPositionType.FILES.ordinal());
        template.setProposalTemplate(proposalTemplate);
        proposalTemplate.addPosition(template);
        template = new ProposalPositionTemplate();
        template.setPositionOrder(3);
        template.setHeader("Select");
        template.setType(ProposalPositionType.LIST.ordinal());
        template.addAnswer(new AnswerTemplate().setAnswer("odpowiedz a")).addAnswer(new AnswerTemplate().setAnswer("Odpowiedz b")).addAnswer(new AnswerTemplate().setAnswer("Odpowiedz C"));
        template.setProposalTemplate(proposalTemplate);
        proposalTemplate.addPosition(template);
        template = new ProposalPositionTemplate();
        template.setPositionOrder(2);
        template.setHeader("Select");
        template.setType(ProposalPositionType.RADIO.ordinal());
        template.addAnswer(new AnswerTemplate().setAnswer("odpowiedz a")).addAnswer(new AnswerTemplate().setAnswer("Odpowiedz b")).addAnswer(new AnswerTemplate().setAnswer("Odpowiedz C"));
        template.setProposalTemplate(proposalTemplate);
        proposalTemplate.addPosition(template);
        template = new ProposalPositionTemplate();
        template.setPositionOrder(1);
        template.setHeader("Text");
        template.setType(ProposalPositionType.TEXT.ordinal());
        template.setProposalTemplate(proposalTemplate);
        proposalTemplate.addPosition(template);
        proposalTemplateRepository.save(proposalTemplate);
        proposalTemplate = new ProposalTemplate();
        proposalTemplate.setName("Przeszczep 2");
        proposalTemplateRepository.save(proposalTemplate);

    }
}
