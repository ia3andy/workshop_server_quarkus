package org.acme;


import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path(value = "/v1/VisitedService")
//@RunOnVirtualThread
public class MultiServiceResource {

    @POST
    @Path("/1")
    public void service1(String body) throws InterruptedException {
        Thread.sleep(200);
    }

    @POST
    @Path("/2")
    public void service2(String body) throws InterruptedException {

        Thread.sleep(300);
    }

    @POST
    @Path("/3")
    public void service3(String body) {
    }




}
