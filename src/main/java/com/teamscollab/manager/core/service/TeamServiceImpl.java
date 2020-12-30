package com.teamscollab.manager.core.service;

import com.teamscollab.manager.core.model.TeamEntity;
import com.teamscollab.manager.core.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
