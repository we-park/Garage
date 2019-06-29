package ekko.wepark.garage.repository;

import ekko.wepark.garage.domain.Garage;
import org.axonframework.commandhandling.model.GenericJpaRepository;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public Repository<Garage> repository(EntityManagerProvider entityManagerProvider, EventBus eventbus) {
        return new GenericJpaRepository<Garage>(entityManagerProvider, Garage.class, eventbus);
    }
}

