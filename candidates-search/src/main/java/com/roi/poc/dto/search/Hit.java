package com.roi.poc.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.roi.poc.dto.domain.Candidate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hit {
    @JsonProperty("_id")
    private Integer id;
    @JsonProperty("_score")
    private Integer score;
    @JsonProperty("_source")
    private Candidate source;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Candidate getSource() {
        return source;
    }

    public void setSource(Candidate source) {
        this.source = source;
    }
}
