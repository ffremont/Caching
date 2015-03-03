/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ffremont.caching;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 *
 * @author florent
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
         packages("fr.ffremont.caching");
    }
}