package bzh.ya2o.dropwizardexample.phonebook.external;

import bzh.ya2o.dropwizardexample.phonebook.interaction.db.repository.*;
import com.google.common.base.*;
import io.dropwizard.auth.*;
import io.dropwizard.auth.basic.*;

public class MyAuthenticator implements Authenticator<BasicCredentials, Boolean> {

    private final MySqlUserRepository userRepository;

    public MyAuthenticator(MySqlUserRepository repository) {
        userRepository = repository;
    }

    public Optional<Boolean> authenticate(BasicCredentials c) throws AuthenticationException {
        boolean validUser = (userRepository.getUser(c.getUsername(), c.getPassword()) == 1);
        if (validUser) {
            return Optional.of(true);
        }
        return Optional.absent();
    }
}
