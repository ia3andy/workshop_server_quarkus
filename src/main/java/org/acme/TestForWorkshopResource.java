package org.acme;


import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.Random;

@Path(value = "/v1/crawl/")
//@RunOnVirtualThread
public class TestForWorkshopResource {

    @Path("{a}/{b}")
    @GET
    public String randomPage(@PathParam("a") String a, @PathParam("b") String b){
        return generateHtmlPageWithUrls(100, "crawl/");
    }

    @Path("/delay/{a}/{b}")
    @GET
    public String randomPageWithDelay(@PathParam("a") String a, @PathParam("b") String b){
        // show what impact the timer has on the threads
        Random r = new Random();
        try {
            Thread.sleep(r.nextInt(10, 200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return generateHtmlPageWithUrls(100, "crawl/delay/");
    }


    private String generateHtmlPageWithUrls(int numberOfUrls, String url){
        StringBuilder s = new StringBuilder();

        Random r = new Random();

        s.append("<html>");
        s.append("<body>");

        for (int i = 0; i < numberOfUrls; i++) {
            int n = r.nextInt(0,numberOfUrls + 1000);
            int n1 = r.nextInt(0,numberOfUrls + 1000);

            s.append("<a href=\"http://localhost:8080/v1/").append(url).append(n1).append("/").append(n).append("\">i am a random url ").append(i).append(" ").append(n1).append("</a><br/>");
        }

        s.append("</body>");
        s.append("</html>");

        return s.toString();
    }
}
