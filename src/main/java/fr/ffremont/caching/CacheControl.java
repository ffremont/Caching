/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ffremont.caching;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

/**
 *
 * @author florent
 */
@Component
@Path("cachecontrol")
public class CacheControl {
    
    @GET
    @Path("use1day")
    public Response use1day(){
        javax.ws.rs.core.CacheControl cache = new javax.ws.rs.core.CacheControl();
        cache.setMaxAge(3600 * 24);
        cache.setSMaxAge(3600 * 24);
        cache.setPrivate(false);
        
        
        return Response.ok("ok").cacheControl(cache).build();
    }
    
    @GET
    @Path("nocache")
    public Response nocache(){
        javax.ws.rs.core.CacheControl cache = new javax.ws.rs.core.CacheControl();
        cache.setMustRevalidate(true);
        cache.setNoCache(true);
        cache.setNoStore(true);
        cache.setPrivate(false);
        
        
        return Response.ok("Pas de cache").cacheControl(cache).build();
    }
}
