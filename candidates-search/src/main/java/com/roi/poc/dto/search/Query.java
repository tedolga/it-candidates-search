package com.roi.poc.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Query {
    @JsonProperty("multi_match")
    private MultiMatchQuery multiMatchQuery;

    public MultiMatchQuery getMultiMatchQuery() {
        return multiMatchQuery;
    }

    public void setMultiMatchQuery(MultiMatchQuery multiMatchQuery) {
        this.multiMatchQuery = multiMatchQuery;
    }
}
