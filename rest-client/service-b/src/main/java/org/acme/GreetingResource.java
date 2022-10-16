package org.acme;

import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOGGER = Logger.getLogger(GreetingResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOGGER.info("GreetingResource#hello");
        return "Hello from Service B";
    }

    @GET
    @Path("/header")
    public String header(@HeaderParam("CustomHeader") String customHeader) {
        LOGGER.info("Custom header " + customHeader);
        return "Received header " + customHeader;
    }


}