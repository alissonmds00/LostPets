package com.alissonmds.LostPets.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final EstadosConverter estadosConverter;

    public WebConfig(EstadosConverter estadosConverter) {
        this.estadosConverter = estadosConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(estadosConverter);
    }
}