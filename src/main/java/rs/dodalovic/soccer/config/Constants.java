package rs.dodalovic.soccer.config;

public class Constants {

    private static final Integer SEASON = 2015;

    public static final String LEAGUES_URL = "http://api.football-data.org/v1/soccerseasons/?season=" + SEASON;
    public static final String TEAMS_URL = "http://api.football-data.org/v1/soccerseasons/%d/teams";
    public static final String TEAM_URL = "http://api.football-data.org/v1/teams/%d";
    public static final String TEAM_PLAYERS_URL = "http://api.football-data.org/v1/teams/%d/players";

}
