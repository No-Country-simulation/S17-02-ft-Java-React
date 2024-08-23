package com.nocountry.telemedicina.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

    // This method could apply an abnormal behavior with the url and paths
    @Override
    public void configurePathMatch(PathMatchConfigurer configured) {
        PathPatternParser patternParser = new PathPatternParser();
        patternParser.setCaseSensitive(false);
        configured.setPatternParser(patternParser);
    }

}