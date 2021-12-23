package com.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.api"})
@PropertySources({
        @PropertySource("environments/${environment}/env.properties"),
        @PropertySource("environments/global.properties")
})
public class SpringConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer getPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${environment}")
    public String environment;

    public String getEnv(){
        return environment;
    }
}
