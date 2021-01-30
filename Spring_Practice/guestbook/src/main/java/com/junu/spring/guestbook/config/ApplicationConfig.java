package com.junu.spring.guestbook.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.junu.spring.guestbook.dao","com.junu.spring.guestbook.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
