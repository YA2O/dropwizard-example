package bzh.ya2o.dropwizardexample.phonebook.external.health;

import bzh.ya2o.dropwizardexample.phoneboook.nucleus.entity.*;
import com.codahale.metrics.health.*;
import com.sun.jersey.api.client.*;
import javax.ws.rs.core.*;

public class NewContactHealthCheck extends HealthCheck {
	private final Client client;

	public NewContactHealthCheck(Client client) {
		super();
		this.client = client;
	}

	@Override
	protected Result check() throws Exception {
		WebResource contactResource = client
				.resource("http://localhost:8080/contact");
		ClientResponse response = contactResource.type(
				MediaType.APPLICATION_JSON).post(
				ClientResponse.class,
				new Contact(0, "Health Check First Name",
						"Health Check Last Name", "00000000"));
		if (response.getStatus() == 201) {
			return Result.healthy();
		} else {
			return Result.unhealthy("New Contact cannot be created!");
		}
	}
}
