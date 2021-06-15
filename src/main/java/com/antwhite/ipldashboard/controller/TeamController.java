package com.antwhite.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import com.antwhite.ipldashboard.model.Match;
import com.antwhite.ipldashboard.model.Team;
import com.antwhite.ipldashboard.service.MatchService;
import com.antwhite.ipldashboard.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/team")
public class TeamController {

    private static final int PAGEABLE_NUMBER = 5;
    private final TeamService teamService;
    private final MatchService matchService;

    @Autowired
    public TeamController(TeamService teamService, MatchService matchService) {
        this.teamService = teamService;
        this.matchService = matchService;
    }


    @GetMapping("")
    public Iterable<Team> getAllTeam() {
        return this.teamService.getAllTeams();
    }

    @GetMapping("/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.teamService.getByTeamName(teamName);
        team.setMatches(matchService.getLatestMatchesByTeam(teamName,PAGEABLE_NUMBER));
        return team;
    }

    @GetMapping("/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return this.matchService.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
    }
}    
