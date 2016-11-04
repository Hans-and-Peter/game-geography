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
    @Path("/{landName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response occupy(@PathParam("landName") String landName, LandUpdate update) {

        Land land = map.lookup(new LandName(landName)); // TODO add factory methods
        Owner newOwner = new Owner(new OwnerName(update.occupier));

        newOwner.occupy(land);

        return Response.ok(asResource(land)).build();
    }

    private LandResource asResource(Land land) {
        LandResource rto = new LandResource();
        rto.landName = land.named().toString(); // TODO (1) create domain extraction without breaking encapsulation, do not use toString
        rto.owner = land.ownedBy().named().toString();
        return rto;
    }
}
