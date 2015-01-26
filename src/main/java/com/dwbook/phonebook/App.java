package com.dwbook.phonebook;

import com.dwbook.phonebook.resources.ContactResource;
import io.dropwizard.jdbi.*;
import org.skife.jdbi.v2.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<PhonebookConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<PhonebookConfiguration> b) {
    }

    @Override
    public void run(PhonebookConfiguration c, Environment e)
            throws Exception {
        LOGGER.info("Method App#run() called");
        for (int i = 0; i < c.getMessageRepetitions(); i++) {
            System.out.println(c.getMessage());
        }

        final DBIFactory dbiFactory = new DBIFactory();
        final DBI jdbi = dbiFactory.build(e, c.getDataSourceFactory(), "mysql");

        // Add the resource to the environment
        e.jersey().register(new ContactResource(jdbi));
    }
}
