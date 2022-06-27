package com.ubitricity.ubicharger.config;

import com.ubitricity.ubicharger.domain.entity.UbiStation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

@Bean
    UbiStation generateUbiStation()
{
    return new UbiStation();
}
}
