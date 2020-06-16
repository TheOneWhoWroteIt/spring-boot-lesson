package com.nevermind.springboot.lesson.config;

import com.nevermind.springboot.lesson.conditional.FirstConditional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Conditional(FirstConditional.class)
@Configuration
public class ApplicationConfiguration {

    /*private static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);*/

    @PostConstruct
    public void init(){
        log.warn("application is start!");
    }
}
