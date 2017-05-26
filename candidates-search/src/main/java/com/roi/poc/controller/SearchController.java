package com.roi.poc.controller;

import com.roi.poc.dto.PageRequest;
import com.roi.poc.dto.SearchResult;
import com.roi.poc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/candidates")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchResult searchCandidates(@RequestParam String query, PageRequest pageRequest) {
        return searchService.getSearchResults(query, pageRequest);
    }
}
