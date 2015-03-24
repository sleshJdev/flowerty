package by.itecharty.flowerty.config;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.runner.RunWith;

import by.itechart.flowerty.configuration.ApplicationConfiguration;
import by.itechart.flowerty.configuration.JpaConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { 
	ApplicationConfiguration.class,
	JpaConfiguration.class })
public class JpaConfigurationAware {

}
