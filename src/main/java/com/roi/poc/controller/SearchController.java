package com.roi.poc.controller;

import com.roi.poc.dto.Candidate;
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
    public Collection<Candidate> searchCandidatesByName(@RequestParam String query) {
        List<Candidate> candidates = new ArrayList<Candidate>();
        Candidate dude = new Candidate();
        dude.setFirstName("Vasya");
        dude.setLastName("Pupkin");
        candidates.add(dude);
        return candidates;
    }
}
