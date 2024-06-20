/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Phuc
 */
@Entity
@Table(name = "surveys")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Surveys.findAll", query = "SELECT s FROM Surveys s"),
    @NamedQuery(name = "Surveys.findBySurveyID", query = "SELECT s FROM Surveys s WHERE s.surveyID = :surveyID"),
    @NamedQuery(name = "Surveys.findBySurveyDate", query = "SELECT s FROM Surveys s WHERE s.surveyDate = :surveyDate"),
    @NamedQuery(name = "Surveys.findByDescription", query = "SELECT s FROM Surveys s WHERE s.description = :description")})
public class Surveys implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SurveyID")
    private Integer surveyID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SurveyDate")
    @Temporal(TemporalType.DATE)
    private Date surveyDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyID")
    private Set<Questions> questionsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyID")
    private Set<Responses> responsesSet;

    public Surveys() {
    }

    public Surveys(Integer surveyID) {
        this.surveyID = surveyID;
    }

    public Surveys(Integer surveyID, Date surveyDate, String description) {
        this.surveyID = surveyID;
        this.surveyDate = surveyDate;
        this.description = description;
    }

    public Integer getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Integer surveyID) {
        this.surveyID = surveyID;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Set<Questions> getQuestionsSet() {
        return questionsSet;
    }

    public void setQuestionsSet(Set<Questions> questionsSet) {
        this.questionsSet = questionsSet;
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
        hash += (surveyID != null ? surveyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Surveys)) {
            return false;
        }
        Surveys other = (Surveys) object;
        if ((this.surveyID == null && other.surveyID != null) || (this.surveyID != null && !this.surveyID.equals(other.surveyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dmp.pojo.Surveys[ surveyID=" + surveyID + " ]";
    }
    
}
