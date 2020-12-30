package com.teamscollab.manager.core.service;

import com.teamscollab.manager.core.model.MatchEntity;
import org.springframework.stereotype.Service;

public interface MatchService {
    MatchEntity getMatch(int i);
}
