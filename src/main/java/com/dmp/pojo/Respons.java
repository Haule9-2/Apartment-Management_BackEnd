package com.dmp.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "responses")
public class Respons {
    @Id
    @Column(name = "ResponseID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SurveyID", nullable = false)
    private Survey surveyID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ResidentID", nullable = false)
    private Resident residentID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "QuestionID", nullable = false)
    private Question questionID;

    @NotNull
    @Lob
    @Column(name = "Answer", nullable = false)
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Survey getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Survey surveyID) {
        this.surveyID = surveyID;
    }

    public Resident getResidentID() {
        return residentID;
    }

    public void setResidentID(Resident residentID) {
        this.residentID = residentID;
    }

    public Question getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Question questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}