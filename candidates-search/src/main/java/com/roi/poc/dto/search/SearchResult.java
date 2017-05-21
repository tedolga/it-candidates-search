package com.roi.poc.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
    @JsonProperty("hits")
    private HitsResult hits;

    public HitsResult getHits() {
        return hits;
    }

    public void setHits(HitsResult hits) {
        this.hits = hits;
    }
}
