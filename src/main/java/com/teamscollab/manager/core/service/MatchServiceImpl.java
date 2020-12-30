package com.teamscollab.manager.core.service;

import com.teamscollab.manager.core.model.MatchEntity;
import com.teamscollab.manager.core.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {


    @Autowired
    private MatchRepository matchRepository;

    @Override
    public MatchEntity getMatch(int i) {
        return matchRepository.getOne(i);
    }
}
