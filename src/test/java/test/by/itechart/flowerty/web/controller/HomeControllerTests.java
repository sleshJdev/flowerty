package test.by.itechart.flowerty.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.mockito.InjectMocks;

import test.by.itechart.flowerty.config.aware.WebApplicationConfigurationAware;
import by.itechart.flowerty.web.controller.HomeController;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         tests for HomeController
 */
public class HomeControllerTests extends WebApplicationConfigurationAware {
    	@InjectMocks
	private HomeController homeControllerMock;
    	
	@Test
	public void index_ShouldReturnViewNameForIndexPage() throws Exception {
	    mockMvc
	    	.perform(get("/"))
		.andExpect(forwardedUrl("home/index"));			
	}
}
