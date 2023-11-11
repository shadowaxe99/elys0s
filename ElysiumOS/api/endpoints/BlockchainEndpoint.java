```java
package api.endpoints;

import blockchain.BlockchainService;
import blockchain.SmartContractManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/blockchain")
public class BlockchainEndpoint {

    private BlockchainService blockchainService = new BlockchainService();
    private SmartContractManager smartContractManager = new SmartContractManager();

    @GET
    @Path("/balance/{address}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalance(@PathParam("address") String address) {
        try {
            String balance = blockchainService.getBalance(address);
            return Response.ok(balance).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/transfer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferFunds(@FormParam("fromAddress") String fromAddress,
                                  @FormParam("toAddress") String toAddress,
                                  @FormParam("amount") String amount) {
        try {
            boolean result = blockchainService.transferFunds(fromAddress, toAddress, amount);
            if (result) {
                return Response.ok("Transfer successful").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Transfer failed").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/contract/{contractAddress}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContractDetails(@PathParam("contractAddress") String contractAddress) {
        try {
            String contractDetails = smartContractManager.getContractDetails(contractAddress);
            return Response.ok(contractDetails).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/deploy")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deployContract(String contractCode) {
        try {
            String contractAddress = smartContractManager.deployContract(contractCode);
            return Response.ok(contractAddress).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
```