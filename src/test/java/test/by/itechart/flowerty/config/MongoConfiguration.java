package test.by.itechart.flowerty.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

/**
 * @author Eugene Putsykovich(slesh) May 6, 2015
 *         <p/>
 *         configuration of mongodb
 */

@Configuration
@Profile("test")
@EnableMongoRepositories
@ComponentScan(basePackages = {MongoConfiguration.TO_SCAN})
public class MongoConfiguration extends AbstractMongoConfiguration {
    protected static final String TO_SCAN = "by.itechart.flowerty.persistence.mongo";

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
        return MongoConfiguration.TO_SCAN;
    }

    @Override
    public Mongo mongo() throws UnknownHostException {
        Mongo mongo = new MongoClient(host, port);

        return mongo;
    }
}
