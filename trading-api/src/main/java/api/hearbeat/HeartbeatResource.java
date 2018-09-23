package api.hearbeat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/heartbeat")
@Produces(MediaType.APPLICATION_JSON)
public class HeartbeatResource {
    @GET
    public HeartbeatResponse heartbeat(@QueryParam("token") String token) {
        return new HeartbeatResponse(token);
    }
}
