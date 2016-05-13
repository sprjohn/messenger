package org.john.javabrains.messenger.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
public class InjectDemoResource {
    @GET
    @Path("annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
                                            @HeaderParam("authSessionId") String header,
                                            @CookieParam("name") String cookie) {
        return "Matrix param: " + matrixParam + "\nHeader param: " + header + "\nCookie value: " + cookie;
    }

    @GET
    @Path("context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
        String path = uriInfo.getAbsolutePath().toString();
        String cookies = httpHeaders.getCookies().toString();

        return "Path: " + path + "\nCookies: " + cookies;
    }
}
