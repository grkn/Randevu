package com.hizliyol.core.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.hizliyol.core.auditing.AuditingAware;

@org.springframework.context.annotation.Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class Configuration {

	@Bean
	@Qualifier("auditorProvider")
    AuditorAware<String> auditorProvider() {
        return new AuditingAware();
    }
}
