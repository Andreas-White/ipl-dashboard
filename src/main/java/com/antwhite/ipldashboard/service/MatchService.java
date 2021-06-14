package com.antwhite.ipldashboard.service;

import com.antwhite.ipldashboard.model.Match;
import com.antwhite.ipldashboard.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getLatestMatchesByTeam(String teamName, int count) {
        return matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }

    public List<Match> getMatchesByTeamBetweenDates(String teamName, LocalDate startDate,LocalDate endDate) {
        return matchRepository.findMatchesByTeamBetweenDates(teamName, startDate, endDate);
    }
}
