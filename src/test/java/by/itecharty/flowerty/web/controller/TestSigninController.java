package by.itecharty.flowerty.web.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.controller.SigninController;
import by.itechart.flowerty.web.service.UserService;
import by.itecharty.flowerty.config.MockTestConfigigurationAware;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 * 
 *         Test for SiginController
 */
public class TestSigninController extends MockTestConfigigurationAware {
	@Mock
	private UserService userServiceMock;

	@InjectMocks
	private SigninController signinControllerMock;

	private MockMvc mock;

	@Before
	public void setUp() {
		mock = MockMvcBuilders.standaloneSetup(signinControllerMock)
				.setHandlerExceptionResolvers(withExceptionControllerAdvice())
				.build();
	}

	@Test
	public void signin_ShouldReturnViewNameForSignin() throws Exception {
		mock
			.perform(get("/signin"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("signin/signin"));			
	}
	
	@Test
	public void signin_PassValidLoginAndPassword_ShouldAuthenticate() throws Exception{
		User existsUser = TestControllerHelper.buildValidShortUserForTest();
		
		when(userServiceMock.findUserByLoginAndPassword(existsUser.getLogin(), existsUser.getPassword()))
			.thenReturn(existsUser);
		
		mock
			.perform(post("/authenticate")
					.param("username", existsUser.getLogin())
					.param("password", existsUser.getPassword())
					)
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("home/index"));
	
		verify(userServiceMock, times(1))
			.findUserByLoginAndPassword(existsUser.getLogin(), existsUser.getPassword());
		verifyNoMoreInteractions(userServiceMock);
	}
	
	@Test
	public void signin_PassInvalidLoginAndPassword_NotAuthenticateShouldRedirectToSigninPage() throws Exception{
		User notExistsUser = TestControllerHelper.buildInvalideShordUserForTest();
		
		when(userServiceMock.findUserByLoginAndPassword(notExistsUser.getLogin(), notExistsUser.getPassword()))
			.thenReturn(null);
		
		mock
			.perform(post("/authenticate")
					.param("username", notExistsUser.getLogin())
					.param("password", notExistsUser.getPassword())
					)
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("signin/signin"));
	
		verify(userServiceMock, times(1))
			.findUserByLoginAndPassword(notExistsUser.getLogin(), notExistsUser.getPassword());
		verifyNoMoreInteractions(userServiceMock);
	}
}
