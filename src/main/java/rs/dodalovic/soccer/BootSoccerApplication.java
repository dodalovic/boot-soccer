package rs.dodalovic.soccer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import rs.dodalovic.soccer.config.ApplicationConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfiguration.class)
public class BootSoccerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootSoccerApplication.class, args);
    }
}
