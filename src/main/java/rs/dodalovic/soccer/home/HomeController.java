package rs.dodalovic.soccer.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.dodalovic.soccer.league.League;
import rs.dodalovic.soccer.league.LeagueCodeToFlag;
import rs.dodalovic.soccer.league.LeaguesService;

import java.util.Collection;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Controller
public class HomeController {

    @Autowired
    private LeaguesService leaguesService;

    @Autowired
    private LeagueCodeToFlag leagueCodeToFlag;

    @RequestMapping("/")
    public String home(Model model) {
        final Collection<League> allLeagues = leaguesService.getAllLeagues();
        model.addAttribute("leagues", allLeagues.stream().map(setLeagueFlag()).collect(toList()));
        return "index";
    }

    private Function<League, League> setLeagueFlag() {
        return league -> {
            league.setCountryFlag(leagueCodeToFlag.flagForLeague(league.getLeague()).orElse("http://placehold" +
                    ".it/800x500"));
            return league;
        };
    }
}
