package test.by.itechart.flowerty.config;

import java.util.Properties;

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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {JpaConfiguration.TO_SCAN})
public class JpaConfiguration extends EmbeddedDataSourceConfig implements TransactionManagementConfigurer {
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

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	entityManagerFactoryBean.setDataSource(configureDataSource());
	entityManagerFactoryBean.setPackagesToScan(JpaConfiguration.TO_SCAN);
	entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

	Properties jpaProperties = new Properties();
	jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);

	entityManagerFactoryBean.setJpaProperties(jpaProperties);
	entityManagerFactoryBean.getJpaPropertyMap().put("jadira.usertype.autoRegisterUserTypes", "true");

	return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
	return new JpaTransactionManager();
    }
}
