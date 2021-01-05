package com.teamscollab.manager.core.controller;

import com.teamscollab.manager.core.model.MatchEntity;
import com.teamscollab.manager.core.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getMatchById(@PathVariable Integer id)
    {
        //matchService.getTeamWinner(matchService.getMatch(id));
        return new ResponseEntity<>(matchService.getMatch(id),HttpStatus.OK);
    }

    @GetMapping("/list-all")
    public ResponseEntity<?> getAllMatches()
    {
        return new ResponseEntity<>(matchService.listAllMatches(), HttpStatus.OK);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> saveMatch(@RequestBody MatchEntity match) {
        MatchEntity savedMatch = matchService.saveMatch(match);

        if (savedMatch != null)
            return new ResponseEntity<>(savedMatch, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update-result/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateMatchResult(@PathVariable Integer id){

        MatchEntity matchToUpdate = matchService.getMatch(id);
        matchService.matchDataUpdater(matchToUpdate);

        return new ResponseEntity<>(matchToUpdate, HttpStatus.OK);
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ResponseEntity<?> dataPeaks() {
        MatchEntity savedTeam = matchService.greatestGoalsBalance();

        if (savedTeam != null)
            return new ResponseEntity<>(matchService.greatestGoalsBalance(), HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
