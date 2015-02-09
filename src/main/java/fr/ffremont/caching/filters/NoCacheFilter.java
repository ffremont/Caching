/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ffremont.caching.filters;

import fr.ffremont.caching.annotations.NoCache;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author florent
 */
@Provider
@NoCache
public class NoCacheFilter implements ContainerResponseFilter{

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        response.getHeaders().putSingle(HttpHeaders.CACHE_CONTROL, "no-cache, no-store");
        response.getHeaders().putSingle(HttpHeaders.EXPIRES, "-1");
    }
    
}
