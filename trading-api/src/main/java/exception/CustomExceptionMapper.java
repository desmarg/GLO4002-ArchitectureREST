package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements
        javax.ws.rs.ext.ExceptionMapper<MappedException> {

    public Response toResponse(MappedException error) {
        return Response.status(error.getStatus()).entity(error).build();
    }
}

