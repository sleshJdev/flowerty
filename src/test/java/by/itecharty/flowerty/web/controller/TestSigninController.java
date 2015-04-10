package by.itecharty.flowerty.web.controller;

import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.controller.LoginController;
import by.itechart.flowerty.web.model.SigninForm;
import by.itechart.flowerty.web.service.UserService;
import by.itecharty.flowerty.config.MockTestConfigigurationAware;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 * 
 *         Test for SiginController
 */
@Ignore
public class TestSigninController extends MockTestConfigigurationAware {
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

	@Test
	public void signin_ShouldReturnViewNameForSignin() throws Exception {
		mock
			.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("signin/signin"));			
	}
	
	@Test
	public void signin_PassValidLoginAndPassword_ShouldAuthenticate() throws Exception{
		User existsUser = TestControllerHelper.buildValidShortUserForTest();
		SigninForm user = new SigninForm(existsUser.getLogin(), existsUser.getPassword());
		
		when(userServiceMock.findUserByLoginAndPassword(existsUser.getLogin(), existsUser.getPassword()))
			.thenReturn(existsUser);
		
		mock
			.perform(post("/authenticate")
					.contentType(TestControllerHelper.APPLICATION_JSON_UTF8)
					.content(TestControllerHelper.convertObjectToJsonBytes(user))
//					.param("login", existsUser.getLogin())
//					.param("password", existsUser.getPassword())
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
		SigninForm user = new SigninForm(notExistsUser.getLogin(), notExistsUser.getPassword());
		
		when(userServiceMock.findUserByLoginAndPassword(notExistsUser.getLogin(), notExistsUser.getPassword()))
			.thenReturn(null);
		
		mock
			.perform(post("/authenticate")
					.contentType(TestControllerHelper.APPLICATION_JSON_UTF8)
					.content(TestControllerHelper.convertObjectToJsonBytes(user))
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
