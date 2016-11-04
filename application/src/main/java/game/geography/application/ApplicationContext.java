package game.geography.application;

import game.geography.geography.LandRepository;
import game.geography.geography.Map;
import game.geography.persistence.InMemoryLandRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {

    @Bean
    public LandRepository landRepository() {
        return new InMemoryLandRepository();
    }

    @Bean
    public Map map(LandRepository repository) {
        return new Map(repository);
    }
}
