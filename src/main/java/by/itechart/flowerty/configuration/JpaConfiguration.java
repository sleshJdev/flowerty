package by.itechart.flowerty.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Configuration for Jpa. User properties file.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { JpaConfiguration.TO_SCAN })
public class JpaConfiguration implements TransactionManagementConfigurer {
    protected static final String TO_SCAN = "by.itechart.flowerty.persistence";

    @Value("${dataSource.driverClassName}")
    private String driver;

    @Value("${dataSource.url}")
    private String url;

    @Value("${dataSource.username}")
    private String username;

    @Value("${dataSource.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.format_sql}")
    private String formatSql;

    @Bean
    public DataSource configureDataSource() {
	HikariConfig config = new HikariConfig();

	config.setDriverClassName(driver);
	config.setJdbcUrl(url);
	config.setUsername(username);
	config.setPassword(password);
	config.addDataSourceProperty("cachePrepStmts", "true");
	config.addDataSourceProperty("prepStmtCacheSize", "250");
	config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
	config.addDataSourceProperty("useServerPrepStmts", "true");

	return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	entityManagerFactoryBean.setDataSource(configureDataSource());
	entityManagerFactoryBean.setPackagesToScan(JpaConfiguration.TO_SCAN);
	entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

	Properties jpaProperties = new Properties();
	jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
	jpaProperties.put(org.hibernate.cfg.Environment.SHOW_SQL, showSql);
	jpaProperties.put(org.hibernate.cfg.Environment.FORMAT_SQL, formatSql);

	entityManagerFactoryBean.setJpaProperties(jpaProperties);
	entityManagerFactoryBean.getJpaPropertyMap().put("jadira.usertype.autoRegisterUserTypes", "true");

	return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
	return new JpaTransactionManager();
    }
}
