package test.by.itechart.flowerty.config.aware;

import by.itechart.flowerty.configuration.LocalConfiguration;
import by.itechart.flowerty.configuration.MailConfiguration;
import by.itechart.flowerty.configuration.SearchContext;
import by.itechart.flowerty.configuration.WebMvcConfiguration;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import test.by.itechart.flowerty.config.ApplicationConfiguration;
import test.by.itechart.flowerty.config.EmbeddedDataSourceConfig;
import test.by.itechart.flowerty.config.JpaConfiguration;
import test.by.itechart.flowerty.config.MongoConfiguration;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {
        ApplicationConfiguration.class,
        LocalConfiguration.class,
        MailConfiguration.class,
        EmbeddedDataSourceConfig.class,
        JpaConfiguration.class,
        MongoConfiguration.class,
        SearchContext.class,
        WebMvcConfiguration.class
})
public abstract class WebApplicationConfigurationAware extends MockTestConfigigurationAware {
    @Inject
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(webApplicationContext)
                .setHandlerExceptionResolvers(withExceptionControllerAdvice())
                .build();
    }
}
