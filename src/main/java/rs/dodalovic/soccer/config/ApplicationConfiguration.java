package rs.dodalovic.soccer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public class ApplicationConfiguration {

    private String soccerapis_base_url;

    public String getSoccerapis_base_url() {
        return soccerapis_base_url;
    }

    public void setSoccerapis_base_url(String soccerapis_base_url) {
        this.soccerapis_base_url = soccerapis_base_url;
    }
}
