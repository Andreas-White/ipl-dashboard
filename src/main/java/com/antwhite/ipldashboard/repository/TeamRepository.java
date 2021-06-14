package com.antwhite.ipldashboard.repository;

import com.antwhite.ipldashboard.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByTeamName(String teamName);
    
}
