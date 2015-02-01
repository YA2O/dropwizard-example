package bzh.ya2o.dropwizardexample.phoneboook.model.repository;

public interface UserRepository {
	int getUser(String username, String password);
}
