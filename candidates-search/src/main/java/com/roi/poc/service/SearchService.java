package com.roi.poc.service;

import com.roi.poc.dto.PageRequest;
import com.roi.poc.dto.SearchResult;
import com.roi.poc.dto.domain.Candidate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchService {
    public SearchResult getSearchResults(String query, PageRequest pageRequest) {
        return new SearchResult(0, pageRequest.getPageNumber(), pageRequest.getPageSize(), new ArrayList<Candidate>());
    }
}
