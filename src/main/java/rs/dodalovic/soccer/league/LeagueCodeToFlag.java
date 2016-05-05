package rs.dodalovic.soccer.league;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class LeagueCodeToFlag {

    private static final Map<String, String> CODE_TO_FLAG;

    static {
        CODE_TO_FLAG = new ImmutableMap.Builder<String, String>()
                .put("BL1", "http://www.sciencekids.co.nz/images/pictures/flags680/Germany.jpg")
                .put("BL2", "http://www.sciencekids.co.nz/images/pictures/flags680/Germany.jpg")
                .put("FL1", "http://www.sciencekids.co.nz/images/pictures/flags680/France.jpg")
                .put("FL2", "http://www.sciencekids.co.nz/images/pictures/flags680/France.jpg")
                .put("PL", "http://www.sciencekids.co.nz/images/pictures/flags680/United_Kingdom.jpg")
                .put("PD", "http://www.sciencekids.co.nz/images/pictures/flags680/Spain.jpg")
                .put("SD", "http://www.sciencekids.co.nz/images/pictures/flags680/Spain.jpg")
                .put("SA", "http://www.sciencekids.co.nz/images/pictures/flags680/Italy.jpg")
                .put("PPL", "http://www.sciencekids.co.nz/images/pictures/flags680/Portugal.jpg")
                .put("BL3", "http://www.sciencekids.co.nz/images/pictures/flags680/Germany.jpg")
                .put("DED", "http://www.sciencekids.co.nz/images/pictures/flags680/Netherlands.jpg")
                .put("EL1", "http://www.sciencekids.co.nz/images/pictures/flags680/United_Kingdom.jpg")
                .put("CL", "https://marcotuliotudo.files.wordpress.com/2015/11/liga-dos-campec3b5es-1.jpg")
                .build();
    }

    public Optional<String> flagForLeague(final String leagueCode) {
        return Optional.ofNullable(CODE_TO_FLAG.get(leagueCode));
    }

}
