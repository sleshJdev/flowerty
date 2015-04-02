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
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.controller.UserController;
import by.itechart.flowerty.web.service.UserService;
import by.itecharty.flowerty.config.MockTestConfigigurationAware;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         Test for UserController
 */
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

	@Test
	public void getById_PassNotValidUserId_ShouldReturnHttpStatusCode404() throws Exception {
		final Long id = Long.MAX_VALUE;

		when(userServiceMock.findOne(id)).thenReturn(null);
		
		mock.perform(get("/user/details/{id}", id))
			.andExpect(status().isOk());

		verify(userServiceMock, times(1)).findOne(id);
		verifyNoMoreInteractions(userServiceMock);
	}
	
	@Test
	public void getById_PassValidUserId_ShouldReturnExistsUser() throws Exception {
		User returnedUser = TestControllerHelper.buildUserAdminForTest();

		when(userServiceMock.findOne(returnedUser.getId())).thenReturn(returnedUser);

		mock
			.perform(get("/user/details/{id}", returnedUser.getId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.login", is(returnedUser.getLogin())))
			.andExpect(jsonPath("$.password", is(returnedUser.getPassword())));

		verify(userServiceMock, times(1))
			.findOne(returnedUser.getId());
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
		User manager = TestControllerHelper.buildUserManagerForTest();
		
		when(userServiceMock.findAll())
			.thenReturn(Arrays.asList(admin, manager));

		mock
			.perform(get("/user/list"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].login", is(admin.getLogin())))
			.andExpect(jsonPath("$[1].login", is(manager.getLogin())))
			.andReturn();
		
		verify(userServiceMock, times(1))
			.findAll();
		verifyNoMoreInteractions(userServiceMock);
	}
	
	@Test
	public void getPage_PassValidPageNumber_ShouldReturnLisUserOnThisPage(){
//		final int pageNumber = 1;
//		final int size = 10;
//		List<User> users = TestControllerHelper.buildValidUserListForTest(size);
//		PageRequest pageRequest = new PageRequest(pageNumber, size);
		//TODO: implement test for this method
		
	}
}
