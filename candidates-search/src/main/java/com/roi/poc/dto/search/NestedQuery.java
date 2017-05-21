package com.roi.poc.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NestedQuery {
    private String path;
    @JsonProperty("score_mode")
    private String scoreMode;
    private Query query;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getScoreMode() {
        return scoreMode;
    }

    public void setScoreMode(String scoreMode) {
        this.scoreMode = scoreMode;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
