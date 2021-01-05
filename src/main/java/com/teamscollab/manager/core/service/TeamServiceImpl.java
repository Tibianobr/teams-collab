package com.teamscollab.manager.core.service;

import com.teamscollab.manager.core.model.TeamEntity;
import com.teamscollab.manager.core.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamEntity createOrUpdateTeam(TeamEntity team) {
        TeamEntity savedTeam = null;
        if (team.getId() == null)
        {
            if (team.getName() != null && !team.getName().isEmpty())
                savedTeam = teamRepository.save(team);
        }
       else
        {
            TeamEntity teamToUpdate = teamRepository.getOne(team.getId());
            teamToUpdate.setAcron(team.getAcron());
            savedTeam = teamRepository.save(teamToUpdate);
        }

        return savedTeam;
    }

    @Override
    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }

    @Override
    public List<TeamEntity> listAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public TeamEntity listById (Integer id) {
        return teamRepository.getOne(id);
    }

    @Override
    public List<TeamEntity> mostWinnerMostLoser() {

        List<TeamEntity> teamsToCompare = listAllTeams();

        // Condição para caso não existam times
        if(teamsToCompare.isEmpty())
            return null;

        TeamEntity winningTeam = new TeamEntity();
        TeamEntity losingTeam = new TeamEntity();

        for(TeamEntity comparingTeam : teamsToCompare) {
            if (comparingTeam.getWins() > winningTeam.getWins()) {
                winningTeam = comparingTeam;
            }

            if (comparingTeam.getLosses() > losingTeam.getLosses()) {
                losingTeam = comparingTeam;
            }
        }

        List<TeamEntity> winnerLoser = new ArrayList<>();
        winnerLoser.add(winningTeam);
        winnerLoser.add(losingTeam);

        return winnerLoser;
    }
}
