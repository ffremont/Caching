package fr.ffremont.microservices.sbmanager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author florent
 */
@SpringBootApplication
public class CachingSpringBootApp extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CachingSpringBootApp.class);
    }

    public static void main(String[] args) {
        new CachingSpringBootApp().configure(new SpringApplicationBuilder(CachingSpringBootApp.class)).run(args);
    }
}
