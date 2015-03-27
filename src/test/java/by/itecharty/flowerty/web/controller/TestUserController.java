package by.itecharty.flowerty.web.controller;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.controller.UserController;
import by.itecharty.flowerty.config.MockTestConfigigurationAware;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         Test for UserController
 */
@Ignore
public class TestUserController extends MockTestConfigigurationAware {
	@Mock
	private UserRepository userRepositoryMock;

	@InjectMocks
	private UserController userControllerMock;

	private MockMvc mock;
	
	@Before
	public void setUp() {
		mock = MockMvcBuilders.standaloneSetup(userControllerMock)
				.setHandlerExceptionResolvers(withExceptionControllerAdvice())
				.build();
	}

	@Test
	public void getById_PassNotValidUserId_ShouldReturnHttpStatusCode404() throws Exception {
		final Long id = Long.MAX_VALUE;

		when(userRepositoryMock.findOne(id)).thenReturn(null);
		
		mock.perform(get("/user/details/{id}", id))
			.andExpect(status()
			.isNotFound());

		verify(userRepositoryMock, times(1)).findOne(id);
		verifyNoMoreInteractions(userRepositoryMock);
	}
	
	@Test
	public void getById_PassValidUserId_ShouldReturnExistsUser() throws Exception {
		User returnedUser = TestControllerHelper.buildUserAdminForTest();

		when(userRepositoryMock.findOne(returnedUser.getId())).thenReturn(returnedUser);

		mock
			.perform(get("/user/details/{id}", returnedUser.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.login", is(returnedUser.getLogin())))
			.andExpect(jsonPath("$.password", is(returnedUser.getPassword())));

		verify(userRepositoryMock, times(1))
			.findOne(returnedUser.getId());
		verifyNoMoreInteractions(userRepositoryMock);
	}

	
	
	@Test
	public void add_PassValidJson_ShouldReturnCreatedUserObject() throws IOException, Exception {
		User returnedUser = TestControllerHelper.buildUserAdminForTest();

		when(userRepositoryMock.save(any(User.class)))
			.thenReturn(returnedUser);
		
		mock
			.perform(post("/user/add")
					.contentType(TestControllerHelper.APPLICATION_JSON_UTF8)
					.content(TestControllerHelper.convertObjectToJsonBytes(returnedUser))
					)
			.andExpect(status().isOk())
			.andExpect(content().contentType(TestControllerHelper.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.login", is(returnedUser.getLogin())))
			.andExpect(jsonPath("$.password", is(returnedUser.getPassword())));
		
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		
		verify(userRepositoryMock, times(1))
			.save(userCaptor.capture());
		verifyNoMoreInteractions(userRepositoryMock);
	}
	
	
	@Test
	public void findAll_ShouldReturnListOfUsers() throws Exception {
		User admin = TestControllerHelper.buildUserAdminForTest();
		User manager = TestControllerHelper.buildUserManagerForTest();
		
		when(userRepositoryMock.findAll())
			.thenReturn(Arrays.asList(admin, manager));

		mock
			.perform(get("/user/list"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].login", is(admin.getLogin())))
			.andExpect(jsonPath("$[1].login", is(manager.getLogin())))
			.andReturn();
		
		verify(userRepositoryMock, times(1))
			.findAll();
		verifyNoMoreInteractions(userRepositoryMock);
	}
}
