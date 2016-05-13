package org.john.javabrains.messenger.resources;

import javax.ws.rs.*;

@Path("/injectdemo")
public class InjectDemoResource {
    @GET
    @Path("annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
                                            @HeaderParam("authSessionId") String header,
                                            @CookieParam("name") String cookie) {
        return "Matrix param: " + matrixParam + "\nHeader param: " + header + "\nCookie value: " + cookie;
    }
}
