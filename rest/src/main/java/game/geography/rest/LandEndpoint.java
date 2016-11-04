package game.geography.rest;

import game.geography.geography.Land;
import game.geography.geography.LandName;
import game.geography.geography.Map;
import game.geography.geography.Owner;
import game.geography.geography.OwnerName;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("land")
public class LandEndpoint {

    @Inject
    private Map map;

    @PUT
    @Path("/{landname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt(@PathParam("landname") String landname, LandUpdate update) {
        Land land = map.lookup(new LandName(landname));
        Owner newOwner = new Owner(new OwnerName(update.owner)); // TODO Factory methods?

        land.owned(newOwner);

        LandResource rto = new LandResource();
        rto.landName = land.named().toString(); // TODO sicha nicht toString
        rto.owner = land.ownedBy().named().toString(); //TODO sicha nicht to String

        return Response.ok(rto).build();
    }
}
