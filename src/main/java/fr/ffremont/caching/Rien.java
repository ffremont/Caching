/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ffremont.caching;

import fr.ffremont.caching.annotations.ExpirationCache;
import fr.ffremont.caching.annotations.NoCache;
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
    @Path("vraiement")
    public Response rien(){
        LOG.debug("Demande rien");
        return Response.ok("Le cache, je m'en fiche !").build();
    }
    
    @GET
    @Path("nocache")
    @NoCache
    public Response nocache(){
        LOG.debug("Annotation @NoCache");
        return Response.ok("Le cache, je m'en fiche !").build();
    }
    
    @GET
    @Path("expiration")
    @ExpirationCache(maxAge = 10)
    public Response exp(){
        LOG.debug("Annotation @ExpirationCache");
        return Response.ok("Le cache, je m'en fiche !").build();
    }
}
