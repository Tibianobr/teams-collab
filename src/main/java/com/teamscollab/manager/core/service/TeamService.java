package com.teamscollab.manager.core.service;

import com.teamscollab.manager.core.model.TeamEntity;

import java.util.List;

public interface TeamService {

    TeamEntity createOrUpdateTeam(TeamEntity team);

    void deleteTeam(Integer id);

    List<TeamEntity> listAllTeams();
}
