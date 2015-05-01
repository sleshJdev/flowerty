package by.itechart.flowerty.config.aware;

import org.junit.runner.RunWith;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import by.itechart.flowerty.config.ApplicationConfiguration;
import by.itechart.flowerty.config.JpaConfiguration;
import by.itechart.flowerty.persistence.model.Phone;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         configuration for testing jpa repositories
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { 
	ApplicationConfiguration.class,
	JpaConfiguration.class })
public class JpaConfigurationAware extends QueryDslRepositorySupport {

    public JpaConfigurationAware() {
	super(Phone.class);
    }

}
