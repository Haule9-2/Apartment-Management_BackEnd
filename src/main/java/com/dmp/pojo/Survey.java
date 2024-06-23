package com.dmp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "surveys")
public class Survey {
    @Id
    @Column(name = "SurveyID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "SurveyDate", nullable = false)
    private LocalDate surveyDate;

    @Size(max = 255)
    @NotNull
    @Column(name = "Description", nullable = false)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(LocalDate surveyDate) {
        this.surveyDate = surveyDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}