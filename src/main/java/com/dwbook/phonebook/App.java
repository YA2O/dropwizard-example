package com.dwbook.phonebook;

import com.dwbook.phonebook.resources.*;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.filter.*;
import io.dropwizard.*;
import io.dropwizard.assets.*;
import io.dropwizard.client.*;
import io.dropwizard.jdbi.*;
import io.dropwizard.setup.*;
import io.dropwizard.views.*;
import org.skife.jdbi.v2.*;
import org.slf4j.*;

public class App extends Application<PhonebookConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<PhonebookConfiguration> b) {
        b.addBundle(new AssetsBundle());
        b.addBundle(new ViewBundle());
    }

    @Override
    public void run(PhonebookConfiguration c, Environment e) throws Exception {
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
        e.jersey().register(new ContactResource(jdbi, e.getValidator()));

        // build the client and add the resource to the environment
        final Client client = new JerseyClientBuilder(e).build("REST Client");
        client.addFilter(new HTTPBasicAuthFilter("john_doe", "secret"));
        e.jersey().register(new ClientResource(client));

       /* // Authenticator, with caching support (CachingAuthenticator)
        CachingAuthenticator<BasicCredentials, Boolean> authenticator = new CachingAuthenticator<BasicCredentials, Boolean>(
                e.metrics(),
                new PhonebookAuthenticator(jdbi),
                CacheBuilderSpec.parse("maximumSize=10000, expireAfterAccess=10m"));

        // Register the authenticator with the environment
        e.jersey().register(new BasicAuthProvider<Boolean>(
                authenticator, "Web Service Realm"));

        // Register the authenticator with the environment
        e.jersey().register(new BasicAuthProvider<Boolean>(
                authenticator, "Web Service Realm"));*/
    }
}
