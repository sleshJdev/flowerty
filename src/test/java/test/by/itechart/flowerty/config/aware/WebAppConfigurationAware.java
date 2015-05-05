package test.by.itechart.flowerty.config.aware;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import test.by.itechart.flowerty.config.ApplicationConfiguration;
import test.by.itechart.flowerty.config.EmbeddedDataSourceConfig;
import test.by.itechart.flowerty.config.JpaConfiguration;
import by.itechart.flowerty.configuration.WebMvcConfiguration;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {
        ApplicationConfiguration.class,
        EmbeddedDataSourceConfig.class,
        JpaConfiguration.class,
        WebMvcConfiguration.class
})
public abstract class WebAppConfigurationAware {

    @Inject
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

}
