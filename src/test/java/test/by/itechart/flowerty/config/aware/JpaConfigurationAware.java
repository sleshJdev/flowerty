package test.by.itechart.flowerty.config.aware;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.by.itechart.flowerty.config.ApplicationConfiguration;
import test.by.itechart.flowerty.config.EmbeddedDataSourceConfig;
import test.by.itechart.flowerty.config.JpaConfiguration;
import test.by.itechart.flowerty.config.MongoConfiguration;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         configuration for testing jpa repositories
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ApplicationConfiguration.class,
        EmbeddedDataSourceConfig.class,
        JpaConfiguration.class,
        MongoConfiguration.class})
public class JpaConfigurationAware {
}
