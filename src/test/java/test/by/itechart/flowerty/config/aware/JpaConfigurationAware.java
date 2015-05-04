package test.by.itechart.flowerty.config.aware;


import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.by.itechart.flowerty.config.ApplicationConfiguration;
import test.by.itechart.flowerty.config.EmbeddedDataSourceConfig;
import test.by.itechart.flowerty.config.JpaConfiguration;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         configuration for testing jpa repositories
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {
        ApplicationConfiguration.class,
        EmbeddedDataSourceConfig.class,
        JpaConfiguration.class})
public class JpaConfigurationAware {
}
