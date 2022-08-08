package org.aibles.worker2.configuration;

import org.aibles.worker2.mapper.Mapper;
import org.aibles.worker2.repository.Repository;
import org.aibles.worker2.service.Service;
import org.aibles.worker2.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@org.springframework.context.annotation.Configuration
@EnableJpaRepositories(basePackages = {"org.aibles.worker2.repository"})
@ComponentScan(basePackages = {"org.aibles.worker2.repository"})
public class Configuration {
    private final ModelMapper modelMapper;

    public Configuration(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
        @Bean
        public Service workerService (Repository repository, Mapper mapper) {
            return new ServiceImpl(repository, mapper, modelMapper);
        }

}
