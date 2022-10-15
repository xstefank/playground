package org.acme.client;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloBean {

    @WithSpan("Hello from my bean")
    public String getHello(@SpanAttribute("Passed name") String name) {
        return "Hello " + name;
    }

}
