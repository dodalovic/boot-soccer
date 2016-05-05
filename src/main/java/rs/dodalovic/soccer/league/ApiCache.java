package rs.dodalovic.soccer.league;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class ApiCache {

    @Autowired
    private SoccerAPI soccerAPI;

    private LoadingCache<String, Collection<League>> allLeaguesCache =
            CacheBuilder.newBuilder()
                    .maximumSize(100)
                    .expireAfterAccess(10, TimeUnit.DAYS)
                    .build(new CacheLoader<String, Collection<League>>() {
                        @Override
                        public Collection<League> load(String key) throws Exception {
                            return soccerAPI.getAllLeagues();
                        }
                    });

    private LoadingCache<Integer, Collection<Team>> teamsInLeagueCache =
            CacheBuilder.newBuilder()
                    .maximumSize(100)
                    .expireAfterAccess(10, TimeUnit.DAYS)
                    .build(new CacheLoader<Integer, Collection<Team>>() {
                        @Override
                        public Collection<Team> load(Integer leagueId) throws Exception {
                            return soccerAPI.getTeamsInLeague(leagueId);
                        }
                    });

    private LoadingCache<Integer, Team> teamsCache =
            CacheBuilder.newBuilder()
                    .maximumSize(100)
                    .expireAfterAccess(1, TimeUnit.DAYS)
                    .build(new CacheLoader<Integer, Team>() {
                        @Override
                        public Team load(Integer teamId) throws Exception {
                            return soccerAPI.getTeam(teamId);
                        }
                    });

    private LoadingCache<Integer, Collection<Player>> teamPlayersCache =
            CacheBuilder.newBuilder()
                    .maximumSize(100)
                    .expireAfterAccess(1, TimeUnit.DAYS)
                    .build(new CacheLoader<Integer, Collection<Player>>() {
                        @Override
                        public Collection<Player> load(Integer teamId) throws Exception {
                            return soccerAPI.getTeamPlayers(teamId);
                        }
                    });


    Collection<League> getAllLeagues() {
        try {
            return allLeaguesCache.get("all");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    Collection<Team> getTeamsInLeague(Integer leagueId) {
        try {
            return teamsInLeagueCache.get(leagueId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    Team getTeam(Integer teamId) {
        try {
            return teamsCache.get(teamId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    Collection<Player> getTeamPlayers(Integer teamId) {
        try {
            return teamPlayersCache.get(teamId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
