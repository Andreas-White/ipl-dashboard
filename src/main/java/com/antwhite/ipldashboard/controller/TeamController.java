package com.antwhite.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import com.antwhite.ipldashboard.model.Match;
import com.antwhite.ipldashboard.model.Team;
import com.antwhite.ipldashboard.service.MatchService;
import com.antwhite.ipldashboard.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {

    private final TeamService teamService;
    private final MatchService matchService;

    @Autowired
    public TeamController(TeamService teamService, MatchService matchService) {
        this.teamService = teamService;
        this.matchService = matchService;
    }


    @GetMapping("/team")
    public Iterable<Team> getAllTeam() {
        return this.teamService.getAllTeams();
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.teamService.getByTeamName(teamName);
        team.setMatches(matchService.getLatestMatchesByTeam(teamName,4));
        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return this.matchService.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
    }
}    
