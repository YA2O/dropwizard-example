package bzh.ya2o.dropwizardexample.phonebook.interaction.db.repository;

import bzh.ya2o.dropwizardexample.phoneboook.model.repository.*;
import org.skife.jdbi.v2.sqlobject.*;

public interface MySqlUserRepository extends UserRepository {

    @SqlQuery("select count(*) from users where username = :username and password = :password")
    int getUser(@Bind("username") String username, @Bind("password") String password);
}
