package com.dwbook.phonebook.resources;

import com.dwbook.phonebook.dao.*;
import com.dwbook.phonebook.representations.*;
import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.skife.jdbi.v2.*;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {

	private final ContactDAO contactDAO;

	public ContactResource(final DBI dbi) {
		contactDAO = dbi.onDemand(ContactDAO.class);
	}

	@GET
	@Path("/{id}")
	public Response getContact(@PathParam("id") int id) {
		// retrieve information about the contact with the provided id
		// ...
		Contact contact = contactDAO.getContactById(id);
		return Response
				.ok(contact)
				.build();
	}

	@POST
	public Response createContact(Contact contact) throws URISyntaxException {
		final int newContactId = contactDAO.createContact(contact.getFirstName(), contact.getLastName(), contact.getPhone());
		return Response
				.created(new URI(String.valueOf(newContactId)))
				.build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteContact(@PathParam("id") int id) {
		contactDAO.deleteContact(id);
		return Response
				.noContent()
				.build();
	}

	@PUT
	@Path("/{id}")
	public Response updateContact(@PathParam("id") int id, Contact contact) {
		contactDAO.updateContact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone());
		return Response
				.ok(new Contact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone()))
				.build();
	}

}
