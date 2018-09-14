package common.service;

import common.repository.RouteRepository;
import common.repository.StationsRepository;
import common.repository.TrainsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManagerFactory;

import static org.mockito.Mockito.mock;

public class MocksConfiguration {
    static TrainsRepository trainsRepository = mock(TrainsRepository.class);
    static StationsRepository stationsRepository = mock(StationsRepository.class);
    static RouteService routeService = mock(RouteService.class);
    static RouteRepository routeRepository = mock(RouteRepository.class);


    @Bean
    @Primary
    public EntityManagerFactory entityManagerFactory(){
        return mock(EntityManagerFactory.class);
    }

    @Bean
    @Primary
    public TrainsRepository trainsRepository(){
        return trainsRepository;
    }

    @Bean
    @Primary
    public StationsRepository stationsRepository(){
        return stationsRepository;
    }

    @Bean
    @Primary
    public RouteService routeService(){
        return routeService;
    }

    @Bean
    @Primary
    public RouteRepository routeRepository(){
        return routeRepository;
    }

}
