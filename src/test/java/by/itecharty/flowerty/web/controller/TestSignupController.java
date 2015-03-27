package by.itecharty.flowerty.web.controller;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.controller.SignupController;
import by.itecharty.flowerty.config.MockTestConfigigurationAware;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 * 
 *         Test for SignupController
 */
@Ignore
public class TestSignupController extends MockTestConfigigurationAware {
	@Mock
	private UserRepository userRepositoryMock;

	@InjectMocks
	private SignupController signupControllerMock;

	private MockMvc mock;

	@Before
	public void setUp() {
		mock = MockMvcBuilders.standaloneSetup(signupControllerMock)
				.setHandlerExceptionResolvers(withExceptionControllerAdvice())
				.build();
	}

	@Test
	public void signup_ShouldReturnViewNameForSignup() throws Exception{
		mock
			.perform(get("/signup"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("signup/signup"));
	}

	@Test
	public void signup_PassSiginFormFromClient_ShouldSaveNewUserAndReturnNameIndexPage() throws Exception{
		User newUser = TestControllerHelper.buildShortUserForTest();
		
		when(userRepositoryMock.save(any(User.class)))
			.thenReturn(newUser);
		
		mock
			.perform(post("/signup")
						.contentType(TestControllerHelper.APPLICATION_JSON_UTF8)
						.content(TestControllerHelper.convertObjectToJsonBytes(newUser))
					)
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("home/index"));
	
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		
		verify(userRepositoryMock, times(1))
			.save(userCaptor.capture());
		verifyNoMoreInteractions(userRepositoryMock);
	}
	
}
