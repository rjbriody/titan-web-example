package com.thinkaurelius.titan.webexample;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.thinkaurelius.titan.core.TitanGraph;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class will intercept all incoming requests and outgoing responses. A transaction is created at the start of
 * every request and committed when the response is sent out. If an error occurs then a rollback is performed. This is a
 * pretty basic transaction strategy.
 */
@Component
public class RequestWrapper implements ContainerResponseFilter, ContainerRequestFilter {

    // Autowired via setter.
    private TitanGraph g;

    @Autowired
    public void setGraph(TitanGraphFactory gf) {
        this.g = gf.getGraph();
    }

    /**
     * Executed before any servlets get the request
     */
    @Override
    public ContainerRequest filter(ContainerRequest containerRequest) {
        g.rollback(); // ensure there is not a stale transaction
        return containerRequest;
    }

    /**
     * Executed after every servlet returns, even if there is an exception.
     */
    @Override
    public ContainerResponse filter(ContainerRequest containerRequest, ContainerResponse containerResponse) {
        if (containerResponse.getStatus() != HttpStatus.SC_OK)
            g.rollback(); // no bueno, rollback.
        else
            g.commit(); // status ok, commit.
        return containerResponse;
    }
}
