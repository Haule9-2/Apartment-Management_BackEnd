package com.dmp.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @Column(name = "QuestionID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SurveyID", nullable = false)
    private Survey surveyID;

    @Size(max = 255)
    @NotNull
    @Column(name = "QuestionText", nullable = false)
    private String questionText;

    @Size(max = 50)
    @NotNull
    @Column(name = "QuestionType", nullable = false, length = 50)
    private String questionType;

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

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

}