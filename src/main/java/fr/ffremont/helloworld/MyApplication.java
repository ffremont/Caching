/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ffremont.helloworld;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author florent
 */
public class MyApplication extends ResourceConfig{
    public MyApplication(){
        packages("fr.ffremont.helloworld");
    }
}
