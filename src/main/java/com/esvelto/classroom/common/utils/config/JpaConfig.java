package com.esvelto.classroom.common.utils.config;

import com.esvelto.classroom.common.utils.models.SpringSecurityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {

    @Bean
    AuditorAware<UUID> auditorProvider(){
        return new SpringSecurityAuditorAware();
    }

}
