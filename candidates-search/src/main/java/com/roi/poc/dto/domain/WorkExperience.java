package com.roi.poc.dto.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WorkExperience {
    @JsonProperty("start_year")
    private Integer startYear;
    @JsonProperty("end_year")
    private Integer endYear;
    @JsonProperty("current_job")
    private Boolean currentJob;
    private String company;
    private String position;
    private List<String> technologies;

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Boolean getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Boolean currentJob) {
        this.currentJob = currentJob;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }
}
