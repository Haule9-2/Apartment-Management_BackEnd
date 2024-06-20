/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Phuc
 */
@Entity
@Table(name = "questions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questions.findAll", query = "SELECT q FROM Questions q"),
    @NamedQuery(name = "Questions.findByQuestionID", query = "SELECT q FROM Questions q WHERE q.questionID = :questionID"),
    @NamedQuery(name = "Questions.findByQuestionText", query = "SELECT q FROM Questions q WHERE q.questionText = :questionText"),
    @NamedQuery(name = "Questions.findByQuestionType", query = "SELECT q FROM Questions q WHERE q.questionType = :questionType")})
public class Questions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "QuestionID")
    private Integer questionID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "QuestionText")
    private String questionText;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "QuestionType")
    private String questionType;
    @JoinColumn(name = "SurveyID", referencedColumnName = "SurveyID")
    @ManyToOne(optional = false)
    private Surveys surveyID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionID")
    private Set<Responses> responsesSet;

    public Questions() {
    }

    public Questions(Integer questionID) {
        this.questionID = questionID;
    }

    public Questions(Integer questionID, String questionText, String questionType) {
        this.questionID = questionID;
        this.questionText = questionText;
        this.questionType = questionType;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
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

    public Surveys getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Surveys surveyID) {
        this.surveyID = surveyID;
    }

    @XmlTransient
    public Set<Responses> getResponsesSet() {
        return responsesSet;
    }

    public void setResponsesSet(Set<Responses> responsesSet) {
        this.responsesSet = responsesSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionID != null ? questionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questions)) {
            return false;
        }
        Questions other = (Questions) object;
        if ((this.questionID == null && other.questionID != null) || (this.questionID != null && !this.questionID.equals(other.questionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dmp.pojo.Questions[ questionID=" + questionID + " ]";
    }
    
}
