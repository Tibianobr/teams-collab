package com.teamscollab.manager.core.controller;

import com.teamscollab.manager.core.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> getTest()
    {
        return new ResponseEntity<>(matchService.getMatch(1),HttpStatus.OK);
    }
}
