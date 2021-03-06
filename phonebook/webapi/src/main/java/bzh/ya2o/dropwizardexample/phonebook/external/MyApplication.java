package bzh.ya2o.dropwizardexample.phonebook.external;

import bzh.ya2o.dropwizardexample.phonebook.external.health.*;
import bzh.ya2o.dropwizardexample.phonebook.interaction.db.repository.*;
import bzh.ya2o.dropwizardexample.phonebook.interaction.http.endpoint.*;
import com.google.common.cache.*;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.filter.*;
import io.dropwizard.*;
import io.dropwizard.auth.*;
import io.dropwizard.auth.basic.*;
import io.dropwizard.client.*;
import io.dropwizard.jdbi.*;
import io.dropwizard.setup.*;
import org.skife.jdbi.v2.*;
import org.slf4j.*;

public class MyApplication extends Application<MyApplicationConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) throws Exception {
        new MyApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<MyApplicationConfiguration> b) {
    }

    @Override
    public void run(MyApplicationConfiguration c, Environment e) throws Exception {
        LOGGER.info("Method App#run() called");
        for (int i = 0; i < c.getMessageRepetitions(); i++) {
            System.out.println(c.getMessage());
        }
        System.out.println(c.getAdditionalMessage());

        // Create a DBI factory and build a JDBI instance
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory
                .build(e, c.getDataSourceFactory(), "mysql");
        // Add the resource to the environment
        e.jersey().register(new ContactEndpoints(jdbi.onDemand(MySqlContactRepository.class), e.getValidator()));

        // build the client and add the resource to the environment
        final Client client = new JerseyClientBuilder(e).build("REST Client");
        client.addFilter(new HTTPBasicAuthFilter("john_doe", "secret"));

        // Authenticator, with caching support (CachingAuthenticator)
        CachingAuthenticator<BasicCredentials, Boolean> authenticator = new CachingAuthenticator<BasicCredentials, Boolean>(
                e.metrics(),
                new MyAuthenticator(jdbi.onDemand(MySqlUserRepository.class)),
                CacheBuilderSpec.parse("maximumSize=10000, expireAfterAccess=10m"));

        // Register the authenticator with the environment
        e.jersey().register(new BasicAuthProvider<Boolean>(
                authenticator, "Web Service Realm"));

        // Register the authenticator with the environment
        e.jersey().register(new BasicAuthProvider<Boolean>(
                authenticator, "Web Service Realm"));

	    // Add health checks
	    e.healthChecks().register("New Contact health check", new NewContactHealthCheck(client));
    }
}
