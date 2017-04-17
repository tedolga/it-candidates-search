package com.roi.poc.controller;

import com.roi.poc.dto.CandidateDto;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/candidates")
public class SearchController {
    @RequestMapping(value = "/name_search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CandidateDto> searchCandidatesByName(@RequestParam String query) {
        List<CandidateDto> candidateDtos = new ArrayList<CandidateDto>();
        CandidateDto vasya = new CandidateDto();
        vasya.setFirstName("Vasya");
        vasya.setLastName("Pupkin");
        candidateDtos.add(vasya);
        return candidateDtos;
    }
}
