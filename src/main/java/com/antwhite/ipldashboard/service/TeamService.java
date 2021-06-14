package com.antwhite.ipldashboard.service;

import com.antwhite.ipldashboard.model.Team;
import com.antwhite.ipldashboard.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getByTeamName(String teamName) {
        return teamRepository.findByTeamName(teamName);
    }
}
