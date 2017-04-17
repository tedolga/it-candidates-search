package com.roi.poc.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SearchController {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}
