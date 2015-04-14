package by.itechart.flowerty.web.controller;

import by.itechart.flowerty.config.MockTestConfigigurationAware;
import by.itechart.flowerty.web.controller.HomeController;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Test for HomeController
 */
public class TestHomeController extends MockTestConfigigurationAware {
	@InjectMocks
	private HomeController homeControllerMock;

	private MockMvc mock;

	@Before
	public void setUp() {
		mock = MockMvcBuilders.standaloneSetup(homeControllerMock)
				.build();
	}

    @Ignore
	@Test
	public void index_ShouldReturnViewNameForIndexPage() throws Exception {
		mock
			.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("home/index"));			
	}
}
