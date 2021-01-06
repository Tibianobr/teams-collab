package com.teamscollab.manager.core.service;

import com.teamscollab.manager.core.model.TeamEntity;

import java.util.List;

public interface TeamService {

    TeamEntity createOrUpdateTeam(TeamEntity team);

    TeamEntity listById (Integer id);

    List<TeamEntity> mostWinnerMostLoser();

<<<<<<< HEAD
    void deleteTeam(Integer id);
=======
    boolean deleteTeam(Integer id);
>>>>>>> estrutura-codigo

    List<TeamEntity> listAllTeams();
}
