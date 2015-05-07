package by.itechart.flowerty.configuration;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.DBAddress;
import com.mongodb.Mongo;

/**
 * @author Eugene Putsykovich(slesh) May 6, 2015
 *
 *         Configuration of MongoDb
 */
@Configuration
public class MongoConfiguration {
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

    @Bean
    public Mongo mongo() throws UnknownHostException {
	DBAddress address = new DBAddress(host, port, database);
	Mongo mongo = Mongo.connect(address).getMongo();

	return mongo;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
	UserCredentials userCredentials = new UserCredentials(username, password);
	SimpleMongoDbFactory factory = new SimpleMongoDbFactory(mongo(), database, userCredentials);

	return factory;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
	MongoTemplate template = new MongoTemplate(mongoDbFactory());

	return template;
    }

}
