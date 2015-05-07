package by.itechart.flowerty.configuration;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import by.itechart.flowerty.Application;

import com.mongodb.DBAddress;
import com.mongodb.Mongo;

/**
 * @author Eugene Putsykovich(slesh) May 6, 2015
 *
 *         configuration of mongodb
 */
@Configuration
@EnableMongoRepositories
@ComponentScan(basePackageClasses = Application.class)
public class MongoConfiguration extends AbstractMongoConfiguration {
    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private int port;

    @Value("${mongo.database}")
    private String database;

    @Value("${mongo.username}")
    private String username;

    @Value("${mongo.password}")
    private String password;

    @Override
    protected UserCredentials getUserCredentials() {
	UserCredentials userCredentials = new UserCredentials(username, password);

	return userCredentials;
    }

    @Override
    protected String getDatabaseName() {
	return database;
    }

    @Override
    protected String getMappingBasePackage() {
	return "by.itechart.flowerty.persistence";
    }

    @Override
    public Mongo mongo() throws UnknownHostException {
	DBAddress address = new DBAddress(host, port, getDatabaseName());
	Mongo mongo = Mongo.connect(address).getMongo();

	return mongo;
    }
}
