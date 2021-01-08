package com.teamscollab.manager.core.service;

import com.teamscollab.manager.core.model.MostWinnerMostLoser;
import com.teamscollab.manager.core.model.TeamEntity;
import com.teamscollab.manager.core.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
    public boolean deleteTeam(Integer id) {

        if(!teamRepository.findById(id).isPresent())
            return false;

        teamRepository.deleteById(id);
        return true;
    }

    @Override
    public List<TeamEntity> listAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public TeamEntity listById (Integer id) {
        return teamRepository.getOne(id);
    }

    /**
     * Returns two teams, containing the one with most wins and with most losses.
     *
     * @return List<TeamEntity> -> The team that has most wins, the team that has the most losses;
     */
    @Override
    public MostWinnerMostLoser mostWinnerMostLoser() {

        List<TeamEntity> teamsToCompare = listAllTeams();

        // Condição para caso não existam times
        if(teamsToCompare.isEmpty())
            return null;

        HashMap<String,TeamEntity> result = calculateWinnerAndLoser(teamsToCompare);

        return new MostWinnerMostLoser(result.get("Winner"),result.get("Loser"));
    }

    public HashMap<String, TeamEntity> calculateWinnerAndLoser(List<TeamEntity> teamsToCompare)
    {
        HashMap<String, TeamEntity> hashMap = new HashMap<>();

        for(TeamEntity comparingTeam : teamsToCompare) {
            if (!hashMap.containsKey("Winner") || comparingTeam.getWins() > hashMap.get("Winner").getWins()) {
                hashMap.put("Winner",comparingTeam) ;
            }

            if (!hashMap.containsKey("Loser") || comparingTeam.getLosses() > hashMap.get("Loser").getLosses()) {
                hashMap.put("Loser",comparingTeam);
            }
        }
        return hashMap;
    }
}
