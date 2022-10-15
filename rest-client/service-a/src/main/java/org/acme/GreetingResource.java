package org.acme;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.acme.client.HelloBean;
import org.acme.client.ServiceB;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOGGER = Logger.getLogger(GreetingResource.class);
    private AtomicLong invocationCounter = new AtomicLong(0);

    @RestClient
    ServiceB serviceB;

    @Inject
    HelloBean helloBean;

    @Inject
    Tracer tracer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(maxRetries = 3)
    @Fallback(fallbackMethod = "fallbackHello")
//    @Timeout(200)
    public String hello() {
        Span span = tracer.spanBuilder("my.custom.span")
            .setAttribute("key", "value")
            .startSpan();

        LOGGER.info("Invocation #" + invocationCounter.getAndIncrement());

        LOGGER.info(helloBean.getHello("PV217"));

        String s = serviceB.customHeader();
        span.end();

        return "Received " + s;
    }

    public String fallbackHello() {
        return "Fallback Hello";
    }
}