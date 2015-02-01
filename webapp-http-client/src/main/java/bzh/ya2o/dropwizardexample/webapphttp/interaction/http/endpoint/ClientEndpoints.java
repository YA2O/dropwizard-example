package bzh.ya2o.dropwizardexample.webapphttp.interaction.http.endpoint;

import bzh.ya2o.dropwizardexample.webapphttp.model.*;
import bzh.ya2o.dropwizardexample.webapphttp.interaction.http.view.*;
import com.sun.jersey.api.client.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Produces(MediaType.TEXT_HTML)
@Path("/client/")
public class ClientEndpoints {

    private Client client;

    public ClientEndpoints(Client client) {
        this.client = client;
    }

    @GET
    @Path("showContact")
    public ContactView showContact(@QueryParam("id") int id) {
        WebResource contactResource = client.resource("http://localhost:8080/contact/" + id);
        Contact c = contactResource.get(Contact.class);
        return new ContactView(c);
    }

    @GET
    @Path("newContact")
    public Response newContact(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("phone") String phone) {
        WebResource contactResource = client.resource("http://localhost:8080/contact");
        ClientResponse response = contactResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, new Contact(0, firstName, lastName, phone));
        if (response.getStatus() == 201) {
            // Created
            return Response.status(302).entity("The contact was created successfully! The new contact can be found at " + response.getHeaders().getFirst("Location")).build();
        } else {
            // Other Status code, indicates an error
            return Response.status(422).entity(response.getEntity(String.class)).build();
        }
    }

    @GET
    @Path("updateContact")
    public Response updateContact(@QueryParam("id") int id, @QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("phone") String phone) {
        WebResource contactResource = client.resource("http://localhost:8080/contact/" + id);
        ClientResponse response = contactResource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, new Contact(id, firstName, lastName, phone));
        if (response.getStatus() == 200) {
            // Created
            return Response.status(302).entity("The contact was updated successfully!").build();
        } else {
            // Other Status code, indicates an error
            return Response.status(422).entity(response.getEntity(String.class)).build();
        }
    }

    @GET
    @Path("deleteContact")
    public Response deleteContact(@QueryParam("id") int id) {
        WebResource contactResource = client.resource("http://localhost:8080/contact/" + id);
        contactResource.delete();
        return Response.noContent().entity("Contact was deleted!").build();
    }
}
