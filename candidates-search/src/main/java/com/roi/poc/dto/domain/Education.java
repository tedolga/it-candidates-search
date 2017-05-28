package com.roi.poc.dto.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Education {
    private String university;
    @JsonProperty("end_year")
    private Integer endYear;
    private String degree;

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
