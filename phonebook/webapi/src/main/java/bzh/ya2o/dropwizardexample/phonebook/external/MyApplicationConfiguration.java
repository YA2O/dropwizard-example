package bzh.ya2o.dropwizardexample.phonebook.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import javax.validation.constraints.Max;
import org.hibernate.validator.constraints.NotEmpty;
import io.dropwizard.db.DataSourceFactory;

public class MyApplicationConfiguration extends Configuration {

	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();

	@JsonProperty
    @NotEmpty
    private String message;

    @JsonProperty
    @Max(10)
    private int messageRepetitions;

    @JsonProperty
    private String additionalMessage = "This is a default value used if not specified otherwise in configuration file";

    public String getMessage() {
        return message;
    }

    public int getMessageRepetitions() {
        return messageRepetitions;
    }

    public String getAdditionalMessage() {
        return additionalMessage;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
