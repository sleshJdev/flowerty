package test.by.itechart.flowerty.web.controller;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import test.by.itechart.flowerty.config.aware.MockTestConfigigurationAware;
import by.itechart.flowerty.persistence.model.User;
import by.itechart.flowerty.web.controller.LoginController;
import by.itechart.flowerty.web.model.SigninForm;
import by.itechart.flowerty.web.service.UserService;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 * 
 *         Test for SiginController
 */
public class SigninControllerTests extends MockTestConfigigurationAware {
	@Mock
	private UserService userServiceMock;

	@InjectMocks
	private LoginController loginControllerMock;

	private MockMvc mock;

	@Before
	public void setUp() {
		mock = MockMvcBuilders.standaloneSetup(loginControllerMock)
				.setHandlerExceptionResolvers(withExceptionControllerAdvice())
				.build();
	}

    @Ignore
	@Test
	public void signin_ShouldReturnViewNameForSignin() throws Exception {
		mock
			.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("signin/signin"));			
	}

    	@Ignore
	@Test
	public void signin_PassValidLoginAndPassword_ShouldAuthenticate() throws Exception{
		User existsUser = ControllerHelperTests.buildValidShortUserForTest();
		SigninForm user = new SigninForm(existsUser.getLogin(), existsUser.getPassword());
		
		when(userServiceMock.findUserByLoginAndPassword(existsUser.getLogin(), existsUser.getPassword()))
			.thenReturn(existsUser);
		
		mock
			.perform(post("/authenticate")
					.contentType(ControllerHelperTests.APPLICATION_JSON_UTF8)
					.content(ControllerHelperTests.convertObjectToJsonBytes(user))
//					.param("login", existsUser.getLogin())
//					.param("password", existsUser.getPassword())
					)
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("home/index"));
	
		verify(userServiceMock, times(1))
			.findUserByLoginAndPassword(existsUser.getLogin(), existsUser.getPassword());
		verifyNoMoreInteractions(userServiceMock);
	}

    	@Ignore
	@Test
	public void signin_PassInvalidLoginAndPassword_NotAuthenticateShouldRedirectToSigninPage() throws Exception{
		User notExistsUser = ControllerHelperTests.buildInvalideShordUserForTest();
		SigninForm user = new SigninForm(notExistsUser.getLogin(), notExistsUser.getPassword());
		
		when(userServiceMock.findUserByLoginAndPassword(notExistsUser.getLogin(), notExistsUser.getPassword()))
			.thenReturn(null);
		
		mock
			.perform(post("/authenticate")
					.contentType(ControllerHelperTests.APPLICATION_JSON_UTF8)
					.content(ControllerHelperTests.convertObjectToJsonBytes(user))
//					.param("login", notExistsUser.getLogin())
//					.param("password", notExistsUser.getPassword())
					)
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("signin/signin"));
	
		verify(userServiceMock, times(1))
			.findUserByLoginAndPassword(notExistsUser.getLogin(), notExistsUser.getPassword());
		verifyNoMoreInteractions(userServiceMock);
	}
}