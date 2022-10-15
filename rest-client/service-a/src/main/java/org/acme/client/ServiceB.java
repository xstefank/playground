package org.acme.client;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import java.util.Random;

@RegisterRestClient(baseUri = "http://localhost:8081")
@ClientHeaderParam(name = "CustomHeader", value = "{computeHeader}")
@Path("/hello")
public interface ServiceB {

    @GET
    String hello();

    @GET
    @Path("/header")
    String customHeader();

    default String computeHeader() {
        return "Dynamic computed header " + new Random().nextDouble();
    }
}
