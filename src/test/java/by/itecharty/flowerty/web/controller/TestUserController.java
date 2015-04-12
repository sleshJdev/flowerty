package by.itecharty.flowerty.web.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.controller.UserController;
import by.itechart.flowerty.web.model.UserEditBundle;
import by.itechart.flowerty.web.service.UserService;
import by.itecharty.flowerty.config.MockTestConfigigurationAware;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         Test for UserController
 */
@Ignore
public class TestUserController extends MockTestConfigigurationAware {
	@Mock
	private UserService userServiceMock;

	@InjectMocks
	private UserController userControllerMock;

	private MockMvc mock;
	
	@Before
	public void setUp() {
		mock = MockMvcBuilders.standaloneSetup(userControllerMock)
				.setHandlerExceptionResolvers(withExceptionControllerAdvice())
				.build();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getById_PassNotValidUserId_ShouldReturnShouldRedirectToErroPage() throws Exception {
		final Long id = Long.MAX_VALUE;

		when(userServiceMock.getUserEditBundleFor(id))
			.thenReturn(null)
			.thenThrow(Exception.class);
		
		mock.perform(get("/user/details/{id}", id))
			.andExpect(status().isOk());

		verify(userServiceMock, times(1)).getUserEditBundleFor(id);
		verifyNoMoreInteractions(userServiceMock);
	}
	
	@Test
	public void getById_PassValidUserId_ShouldReturnUserEditBundle() throws Exception {
		UserEditBundle bundle = TestControllerHelper.buildUserEditBundleForTest();
		User returnedUser = bundle.getUser();
		
		when(userServiceMock.getUserEditBundleFor(returnedUser.getId())).thenReturn(bundle);
		
		mock
			.perform(get("/user/details/{id}", returnedUser.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.user.id", is(1)))
			.andExpect(jsonPath("$.user.login", is(returnedUser.getLogin())))
			.andExpect(jsonPath("$.user.password", is(returnedUser.getPassword())));

		verify(userServiceMock, times(1)).getUserEditBundleFor(returnedUser.getId());
		verifyNoMoreInteractions(userServiceMock);
	}

	@Test
	public void add_PassValidJson_ShouldReturnCreatedUserObject() throws IOException, Exception {
		User returnedUser = TestControllerHelper.buildUserAdminForTest();

		when(userServiceMock.save(any(User.class)))
			.thenReturn(returnedUser);
		
		mock
			.perform(post("/user/save")
					.contentType(TestControllerHelper.APPLICATION_JSON_UTF8)
					.content(TestControllerHelper.convertObjectToJsonBytes(returnedUser))
					)
			.andExpect(status().isOk())
			.andExpect(content().contentType(TestControllerHelper.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.login", is(returnedUser.getLogin())))
			.andExpect(jsonPath("$.password", is(returnedUser.getPassword())));
		
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		
		verify(userServiceMock, times(1))
			.save(userCaptor.capture());
		verifyNoMoreInteractions(userServiceMock);
	}
	
	
	@Test
	public void findAll_ShouldReturnListOfUsers() throws Exception {
		User admin = TestControllerHelper.buildUserAdminForTest();
		
		when(userServiceMock.findAll())
			.thenReturn(Arrays.asList(admin, admin));

		mock
			.perform(get("/user/list"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].login", is(admin.getLogin())))
			.andExpect(jsonPath("$[1].login", is(admin.getLogin())))
			.andReturn();
		
		verify(userServiceMock, times(1))
			.findAll();
		verifyNoMoreInteractions(userServiceMock);
	}
	
	@Ignore
	@Test
	public void getPage_PassValidPageNumber_ShouldReturnLisUserOnThisPage() {
		// TODO: need implements this test
	}
}
