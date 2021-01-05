package com.teamscollab.manager.core.controller;

import com.teamscollab.manager.core.model.TeamEntity;
import com.teamscollab.manager.core.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // CREATE, UPDATE
    @RequestMapping(value = "/new", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createOrUpdateTeam(@RequestBody TeamEntity team)
    {
        TeamEntity createdTeam = teamService.createOrUpdateTeam(team);
        if (createdTeam != null && team.getId() == null) // Caso de criar um novo time
            return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
        else if (createdTeam != null && team.getId() != null) // Caso de atualizar um time
            return new ResponseEntity<>(createdTeam, HttpStatus.OK);
        else // Deu erro na chamada
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // LIST
    @RequestMapping(value = "/list-all", method = RequestMethod.GET)
    public ResponseEntity<?> listAllTeams()
    {
        return new ResponseEntity<>(teamService.listAllTeams(),HttpStatus.OK);
    }

    // GET TEAM
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> listById(@PathVariable Integer id) {
        TeamEntity savedTeam = teamService.listById(id);

        if (savedTeam.getId() != null)
            return new ResponseEntity<>(savedTeam, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/winner-loser", method = RequestMethod.GET)
    public ResponseEntity<?> dataPeaks() {
        List<TeamEntity> savedTeam = teamService.mostWinnerMostLoser();

        if (savedTeam != null)
            return new ResponseEntity<>(teamService.mostWinnerMostLoser(), HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
