package com.teamscollab.manager.core.repository;

import com.teamscollab.manager.core.model.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity,Integer> {
}
