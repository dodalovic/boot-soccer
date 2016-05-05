package rs.dodalovic.soccer.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/leagues")
public class LeagueController {

    @Autowired
    private LeaguesService leaguesService;

    @RequestMapping(value = "/{leagueId}/teams", method = RequestMethod.GET)
    public String showLeagueTeams(Model model, @PathVariable Integer leagueId) {
        model.addAttribute("leagueTeams", leaguesService.getTeamsInLeague(leagueId));
        model.addAttribute("leagueId", leagueId);
        return "leagueTeams";
    }

    @RequestMapping(value = "/{leagueId}/teams/{teamId}", method = RequestMethod.GET)
    public String showSingleTeam(Model model, @PathVariable Integer leagueId, @PathVariable Integer teamId) {
        final Team team = leaguesService.getTeam(teamId);
        team.setPlayers(leaguesService.getPlayersForTeam(teamId));
        model.addAttribute("team", team);
        return "leagueTeam";
    }
}
