package by.itechart.flowerty.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import by.itechart.flowerty.config.MockTestConfigigurationAware;

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

	@Test
	public void index_ShouldReturnViewNameForIndexPage() throws Exception {
		mock
			.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("home/index"));			
	}
}
