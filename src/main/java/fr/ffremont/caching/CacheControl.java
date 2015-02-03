/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ffremont.caching;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author florent
 */
@Component
@Path("cachecontrol")
public class CacheControl {

    @Context
    private HttpServletRequest request;
    
    private final static Logger LOG = LoggerFactory.getLogger(CacheControl.class);
    
    @GET
    @Path("use60sec")
    public Response use60sec() {
        javax.ws.rs.core.CacheControl cache = new javax.ws.rs.core.CacheControl();
        cache.setMaxAge(60);
        cache.setPrivate(false);

        return Response.ok("En cache pendant 60 sec").cacheControl(cache).build();
    }

    @GET
    @Path("disablecache")
    public Response disablecache() {
        javax.ws.rs.core.CacheControl cache = new javax.ws.rs.core.CacheControl();
        cache.setNoCache(true); // pour IE 
        cache.setNoStore(true); // HTTP 1.1 demande juste no-store

        return Response.ok("Pas de cache").cacheControl(cache).build();
    }

    @GET
    @Path("nostore")
    public Response nostore() {
        javax.ws.rs.core.CacheControl cache = new javax.ws.rs.core.CacheControl();
        cache.setNoStore(true);

        return Response.ok("No store").cacheControl(cache).build();
    }

    @GET
    @Path("nocache")
    public Response nocache() {
        javax.ws.rs.core.CacheControl cache = new javax.ws.rs.core.CacheControl();
        cache.setNoCache(true);

        return Response.ok("No cache").cacheControl(cache).build();
    }
    
    @GET
    @Path("validationtps")
    public Response validationTps() {
        javax.ws.rs.core.CacheControl cache = new javax.ws.rs.core.CacheControl();
        cache.setMaxAge(60);
        cache.setPrivate(false);
        
        if(request.getHeader("If-Modified-Since") != null){
            // ma ressource est encore bonne
            return Response.status(Response.Status.NOT_MODIFIED).build();            
        }        

        return Response.ok("Ma petite donnée").lastModified(Date.from(Instant.now())).cacheControl(cache).build();
    }
}
