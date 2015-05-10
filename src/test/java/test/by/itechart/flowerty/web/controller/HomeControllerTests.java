package test.by.itechart.flowerty.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.mockito.InjectMocks;

import test.by.itechart.flowerty.config.aware.WebApplicationConfigurationAware;
import by.itechart.flowerty.web.controller.HomeController;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Test for HomeController
 */
public class HomeControllerTests extends WebApplicationConfigurationAware {
    	@InjectMocks
	private HomeController homeControllerMock;

	@Test
	public void index_ShouldReturnViewNameForIndexPage() throws Exception {
	    mockMvc
			.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("home/index"));			
	}
}
