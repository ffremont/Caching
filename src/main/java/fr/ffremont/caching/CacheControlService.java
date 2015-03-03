/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ffremont.caching;

import java.time.Instant;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
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
public class CacheControlService {

    @Context
    private Request request;
    
    private final static Logger LOG = LoggerFactory.getLogger(CacheControlService.class);
    
    @GET
    @Path("use10sec")
    public Response use10sec() {
        javax.ws.rs.core.CacheControl cache = new javax.ws.rs.core.CacheControl();
        cache.setMaxAge(10);
        cache.setPrivate(false);

        LOG.info("Appel use10sec");
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
    
    /**
     * Utilisation du If-Modified-Since
     *  Ma ressource est mise en cache pendant 10sec puis une revalidation à lieu pour renouveler les 10sec.
     *  Sauf sur IE, passé 10sec, la revalidation ne renouvele pas les 10sec
     * 
     * @param httpRequest
     * @return 
     */
    @GET
    @Path("validationtps")
    public Response validationTps(@Context HttpServletRequest httpRequest) {
        javax.ws.rs.core.CacheControl cache = new javax.ws.rs.core.CacheControl();
        cache.setMaxAge(10); 
        cache.setMustRevalidate(true);
        cache.setPrivate(false);
        Instant updated = Instant.parse("2011-12-03T10:15:30Z");
                
        LOG.info("Cache par validation de temps");
        Response.ResponseBuilder builder = request.evaluatePreconditions(Date.from(updated));
        if(builder == null){
            builder = Response.ok("Ma petite donnée").lastModified(Date.from(updated));
        }
        builder.cacheControl(cache);
        
        return builder.build();
    }
    
    @GET
    @Path("vcontenu10sec")
    public Response vcontenu30sec() {
        String hashOfMyContent = "azerty_v1";
        EntityTag etag = new EntityTag(hashOfMyContent);
        CacheControl cache = new CacheControl();
        cache.setMaxAge(10);
        
        Response.ResponseBuilder builder = request.evaluatePreconditions(etag);
        if(builder == null){
            LOG.info("Retour du contenu");
            builder = Response.ok("Cache par validation de contenu");
        }
        builder.cacheControl(cache).tag(etag);
        
        LOG.info("Retour 304");
        return builder.build();
    }
    
    @GET
    @Path("vcontenu")
    public Response vcontenu(@Context HttpServletRequest httpRequest) {
        String hashOfMyContent = "azerty_v1";
        EntityTag etag = new EntityTag(hashOfMyContent);
        
        Response.ResponseBuilder builder = request.evaluatePreconditions(etag);
        if(builder == null){
            LOG.info("Retour du contenu");
            builder = Response.ok("Cache par validation de contenu");
        }
        builder.tag(etag);
        
        LOG.info("Retour 304");
        return builder.build();
    }
    
    @GET
    @Path("vcontenu_ie")
    public Response vcontenuIe(@Context HttpServletRequest httpRequest) {
        String hashOfMyContent = "azerty_v1";
        EntityTag etag = new EntityTag(hashOfMyContent);
        CacheControl cache = new CacheControl();
        cache.setNoCache(true);
        
        Response.ResponseBuilder builder = request.evaluatePreconditions(etag);
        if(builder == null){
            LOG.info("Retour du contenu");
            builder = Response.ok("Cache par validation de contenu").cacheControl(cache).tag(etag);
        }
        
        LOG.info("Retour 304");
        return builder.build();
    }
}
