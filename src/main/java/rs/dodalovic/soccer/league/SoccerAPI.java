package rs.dodalovic.soccer.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rs.dodalovic.soccer.config.ApplicationConfiguration;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static rs.dodalovic.soccer.config.Constants.*;
import static rs.dodalovic.soccer.league.SoccerAPI.Endpoints.*;

@Component
public class SoccerAPI {

    @Value("${soccerapis_token}")
    private String apiToken;

    @Autowired
    private ApplicationConfiguration configuration;

    Collection<League> getAllLeagues() {
        final ResponseEntity<League[]> entity = get(LEAGUES.url(), League[].class);
        final League[] leagues = entity.getBody();
        return Arrays.stream(leagues).collect(Collectors.toList());
    }

    Collection<Team> getTeamsInLeague(Integer leagueId) {
        final ResponseEntity<League> responseEntity = get(format(TEAMS.url(), leagueId), League.class);
        return responseEntity.getBody().getTeams();
    }

    Team getTeam(Integer teamId) {
        return get(format(TEAM.url(), teamId), Team.class).getBody();
    }

    private HttpHeaders requestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Response-Control", "minified");
        headers.set("X-Auth-Token", apiToken);
        return headers;
    }

    private <T> ResponseEntity<T> get(String url, Class<T> tClass) {
        return new RestTemplate().exchange(URI.create(url), HttpMethod.GET, new HttpEntity<T>(requestHeaders()),
                tClass);
    }

    List<Player> getTeamPlayers(Integer teamId) {
        final TeamPlayers getTeamPlayers = get(format(TEAM_PLAYERS.url(), teamId), TeamPlayers.class).getBody();
        return getTeamPlayers.getPlayers();
    }

    enum Endpoints {
        LEAGUES {
            @Override
            public String url() {
                return LEAGUES_URL;
            }
        },
        TEAMS {
            @Override
            public String url() {
                return TEAMS_URL;
            }
        },
        TEAM {
            @Override
            public String url() {
                return TEAM_URL;
            }
        },
        TEAM_PLAYERS {
            @Override
            public String url() {
                return TEAM_PLAYERS_URL;
            }
        };

        public abstract String url();
    }
}
