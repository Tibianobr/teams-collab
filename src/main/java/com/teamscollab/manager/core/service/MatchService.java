package com.teamscollab.manager.core.service;

import com.teamscollab.manager.core.model.MatchEntity;
import com.teamscollab.manager.core.model.TeamEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MatchService {
    MatchEntity getMatch(int i);

    MatchEntity matchDataUpdater(MatchEntity match);

    boolean deleteMatch(Integer id);

    List<MatchEntity> listAllMatches();

    MatchEntity greatestGoalsBalance();

    MatchEntity saveMatch(MatchEntity match);
}
