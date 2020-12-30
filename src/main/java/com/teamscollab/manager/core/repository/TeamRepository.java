package com.teamscollab.manager.core.repository;

import com.teamscollab.manager.core.model.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity,Integer> {
}
