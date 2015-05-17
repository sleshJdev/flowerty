package test.by.itechart.flowerty.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * @author Eugene Putsykovich(slesh) May 17, 2015
 *
 *         the data source config that can be used in integration tests.
 */
@Configuration
public class EmbeddedDataSourceConfig {
    @Bean
    public DataSource dataSource() {
	return new EmbeddedDatabaseBuilder()
		.setType(EmbeddedDatabaseType.HSQL)
		.addScript("classpath:/flowerty-dump.sql")
		.build();
    }
}
