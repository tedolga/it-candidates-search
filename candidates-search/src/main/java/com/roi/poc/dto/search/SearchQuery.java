package com.roi.poc.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchQuery {
    @JsonProperty("nested")
    private NestedQuery nestedQuery;

    public NestedQuery getNestedQuery() {
        return nestedQuery;
    }

    public void setNestedQuery(NestedQuery nestedQuery) {
        this.nestedQuery = nestedQuery;
    }
}
