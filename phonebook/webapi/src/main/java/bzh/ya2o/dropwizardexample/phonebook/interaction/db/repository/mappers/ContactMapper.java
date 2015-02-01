package bzh.ya2o.dropwizardexample.phonebook.interaction.db.repository.mappers;

import bzh.ya2o.dropwizardexample.phoneboook.nucleus.entity.*;
import java.sql.*;
import org.skife.jdbi.v2.*;
import org.skife.jdbi.v2.tweak.*;

public class ContactMapper implements ResultSetMapper<Contact> {

	public Contact map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Contact(
				r.getInt("id"),
				r.getString("firstName"),
				r.getString("lastName"),
				r.getString("phone")
		);
	}
}
