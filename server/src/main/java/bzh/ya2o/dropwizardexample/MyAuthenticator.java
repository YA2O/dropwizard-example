package bzh.ya2o.dropwizardexample;

import org.skife.jdbi.v2.DBI;
import bzh.ya2o.dropwizardexample.repository.UserDAO;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class MyAuthenticator implements Authenticator<BasicCredentials, Boolean> {

    private final UserDAO userDao;

    public MyAuthenticator(DBI jdbi) {
        userDao = jdbi.onDemand(UserDAO.class);
    }

    public Optional<Boolean> authenticate(BasicCredentials c) throws AuthenticationException {
        boolean validUser = (userDao.getUser(c.getUsername(), c.getPassword()) == 1);
        if (validUser) {
            return Optional.of(true);
        }
        return Optional.absent();
    }
}
