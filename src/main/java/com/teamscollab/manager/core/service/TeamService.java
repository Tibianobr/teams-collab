package com.teamscollab.manager.core.service;

import com.teamscollab.manager.core.model.MostWinnerMostLoser;
import com.teamscollab.manager.core.model.TeamEntity;

import java.util.List;

public interface TeamService {

    TeamEntity createOrUpdateTeam(TeamEntity team);

    TeamEntity listById (Integer id);

    MostWinnerMostLoser mostWinnerMostLoser();

    boolean deleteTeam(Integer id);

    List<TeamEntity> listAllTeams();
}
