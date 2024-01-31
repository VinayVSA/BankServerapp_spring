package com.cg.bsappl;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.cg.bsappl.dao")
public class JPAConfig {

}
