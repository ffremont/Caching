/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ffremont.helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author florent
 */
@Component
@Path("rien")
public class Rien {
    private final static Logger LOG = LoggerFactory.getLogger(Rien.class);
    
    @GET
    public Response rien(){
        LOG.debug("Demande rien");
        return Response.ok("Le cache, je m'en fiche !").build();
    }
}
