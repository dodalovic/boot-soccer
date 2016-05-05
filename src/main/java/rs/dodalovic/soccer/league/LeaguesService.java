package rs.dodalovic.soccer.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LeaguesService {

    @Autowired
    private ApiCache apiCache;

    public Collection<League> getAllLeagues() {
        return apiCache.getAllLeagues();
    }

    Collection<Team> getTeamsInLeague(Integer leagueId) {
        return apiCache.getTeamsInLeague(leagueId);
    }

    Team getTeam(Integer teamId) {
        return apiCache.getTeam(teamId);
    }

    Collection<Player> getPlayersForTeam(Integer teamId) {
        return apiCache.getTeamPlayers(teamId);
    }
}
