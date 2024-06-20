/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phuc
 */
@Entity
@Table(name = "responses")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Responses.findAll", query = "SELECT r FROM Responses r"),
        @NamedQuery(name = "Responses.findByResponseID", query = "SELECT r FROM Responses r WHERE r.responseID = :responseID"),
        @NamedQuery(name = "Responses.findByAnswer", query = "SELECT r FROM Responses r WHERE r.answer = :answer")})
public class Responses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ResponseID")
    private Integer responseID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Answer")
    private int answer;
    @JoinColumn(name = "QuestionID", referencedColumnName = "QuestionID")
    @ManyToOne(optional = false)
    private Questions questionID;
    @JoinColumn(name = "ResidentID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Resident residentID;
    @JoinColumn(name = "SurveyID", referencedColumnName = "SurveyID")
    @ManyToOne(optional = false)
    private Surveys surveyID;

    public Responses() {
    }

    public Responses(Integer responseID) {
        this.responseID = responseID;
    }

    public Responses(Integer responseID, int answer) {
        this.responseID = responseID;
        this.answer = answer;
    }

    public Integer getResponseID() {
        return responseID;
    }

    public void setResponseID(Integer responseID) {
        this.responseID = responseID;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Questions getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Questions questionID) {
        this.questionID = questionID;
    }

    public Resident getResidentID() {
        return residentID;
    }

    public void setResidentID(Resident residentID) {
        this.residentID = residentID;
    }

    public Surveys getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Surveys surveyID) {
        this.surveyID = surveyID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (responseID != null ? responseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responses)) {
            return false;
        }
        Responses other = (Responses) object;
        if ((this.responseID == null && other.responseID != null) || (this.responseID != null && !this.responseID.equals(other.responseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dmp.pojo.Responses[ responseID=" + responseID + " ]";
    }

}
