package test.by.itechart.flowerty.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Ignore;
import org.junit.Test;

import test.by.itechart.flowerty.config.aware.WebApplicationConfigurationAware;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *	
 *         tests for HomeController
 */

@Ignore
public class HomeControllerTest extends WebApplicationConfigurationAware {

    @Test
    public void index_ShouldReturnViewNameForIndexPage() throws Exception {
	mockMvc
		.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("home/index"))
		.andExpect(forwardedUrl("/WEB-INF/views/home/index.html"));
    }
}
