package bzh.ya2o.dropwizardexample.webapphttp.external;

import bzh.ya2o.dropwizardexample.webapphttp.interaction.http.endpoint.*;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.filter.*;
import io.dropwizard.*;
import io.dropwizard.assets.*;
import io.dropwizard.client.*;
import io.dropwizard.setup.*;
import io.dropwizard.views.*;
import org.slf4j.*;

public class MyApplication extends Application<MyApplicationConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) throws Exception {
        new MyApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<MyApplicationConfiguration> b) {
        b.addBundle(new AssetsBundle());
        b.addBundle(new ViewBundle());
    }

    @Override
    public void run(MyApplicationConfiguration c, Environment e) throws Exception {
        LOGGER.info("Method App#run() called");

	    // build the client and add the resource to the environment
	    final Client client = new JerseyClientBuilder(e).build("REST Client");
	    client.addFilter(new HTTPBasicAuthFilter("john_doe", "secret"));
	    e.jersey().register(new ClientEndpoints(client));
    }
}
