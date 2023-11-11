package api.endpoints;

import arena.GameManager;
import arena.ScenarioGenerator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/arena")
public class ArenaEndpoint {

    private GameManager gameManager = new GameManager();
    private ScenarioGenerator scenarioGenerator = new ScenarioGenerator();

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArenaStatus() {
        return Response.ok(gameManager.getArenaStatus()).build();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createScenario(String scenarioConfig) {
        try {
            String scenarioId = scenarioGenerator.createScenario(scenarioConfig);
            return Response.ok("{\"scenarioId\": \"" + scenarioId + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("/execute")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response executeScenario(String scenarioId) {
        try {
            boolean success = gameManager.executeScenario(scenarioId);
            if (success) {
                return Response.ok("{\"message\": \"Scenario executed successfully.\"}").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Failed to execute scenario.\"}").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }
}