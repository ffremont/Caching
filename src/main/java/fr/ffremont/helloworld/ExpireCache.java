/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ffremont.helloworld;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Date;
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
@Path("expirecache")
public class ExpireCache {
    private final static Logger LOG = LoggerFactory.getLogger(ExpireCache.class);
    
    /**
     * Expire 1 mois
     */
    @GET
    @Path("use1month")
    public Response use120sec(){
        Date expires = Date.from(Instant.now().plus(30, ChronoUnit.DAYS));
        
        return Response.ok("ok").expires(expires).build();
    }
    
    
}
