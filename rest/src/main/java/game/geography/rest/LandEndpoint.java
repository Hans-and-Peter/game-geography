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
        String occupier = update.occupier;

        Land land = occupyLand(landName, occupier);

        return Response.ok(asResource(land)).build();
    }

    // TODO DDD: create command for occupyLand so I see the verb of the domain in classes, too.  
    private Land occupyLand(String landName, String occupier) {
        // TODO DDD: add factory methods for LandName, OwnerName etc.
        Land land = map.lookup(new LandName(landName));
        Owner newOwner = new Owner(new OwnerName(occupier));
        newOwner.occupy(land);
        return land;
    }

    private LandResource asResource(Land land) {
        LandResource rto = new LandResource();
        // TODO (1) DDD: create domain extraction without breaking encapsulation, do not use toString
        rto.landName = land.named().toString();
        rto.owner = land.ownedBy().named().toString();
        return rto;
    }
}
