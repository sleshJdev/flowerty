package test.by.itechart.flowerty.web.controller;

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

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;

import test.by.itechart.flowerty.config.aware.WebApplicationConfigurationAware;
import by.itechart.flowerty.persistence.model.User;
import by.itechart.flowerty.web.controller.UserController;
import by.itechart.flowerty.web.model.UserEditBundle;
import by.itechart.flowerty.web.service.UserService;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *         
 *         test for UserController
 */
public class UserControllerTests extends WebApplicationConfigurationAware {
    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private UserController userControllerMock;

    @SuppressWarnings("unchecked")
    @Test
    public void getById_PassNotValidUserId_ShouldRedirectToErroPage() throws Exception {
        final Long id = Long.MAX_VALUE;

        when(userServiceMock.getUserEditBundleFor(id))
                .thenReturn(null)
                .thenThrow(Exception.class);

        mockMvc
        	.perform(get("/user/details/{id}", id))
                .andExpect(status().isOk());

        verify(userServiceMock, times(1)).getUserEditBundleFor(id);
        verifyNoMoreInteractions(userServiceMock);
    }

    @Ignore
    @Test
    public void getById_PassValidUserId_ShouldReturnUserEditBundle() throws Exception {
        UserEditBundle bundle = HelperTestsController.buildUserEditBundleForTest();
        User returnedUser = bundle.getUser();

        when(userServiceMock.getUserEditBundleFor(returnedUser.getId())).thenReturn(bundle);

        mockMvc
                .perform(get("/user/details/{id}", returnedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.user.id", is(1)))
                .andExpect(jsonPath("$.user.login", is(returnedUser.getLogin())))
                .andExpect(jsonPath("$.user.password", is(returnedUser.getPassword())));

        verify(userServiceMock, times(1)).getUserEditBundleFor(returnedUser.getId());
        verifyNoMoreInteractions(userServiceMock);
    }

    @Ignore
    @Test
    public void add_PassValidJson_ShouldReturnCreatedUserObject() throws IOException, Exception {
        User returnedUser = HelperTestsController.buildUserAdminForTest();

        when(userServiceMock.save(any(User.class)))
                .thenReturn(returnedUser);

        mockMvc
                .perform(post("/user/save")
                                .contentType(HelperTestsController.APPLICATION_JSON_UTF8)
                                .content(HelperTestsController.convertObjectToJsonBytes(returnedUser))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(HelperTestsController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.login", is(returnedUser.getLogin())))
                .andExpect(jsonPath("$.password", is(returnedUser.getPassword())));

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(userServiceMock, times(1))
                .save(userCaptor.capture());
        verifyNoMoreInteractions(userServiceMock);
    }

	/*@Test

    @Ignore
	@Test
>>>>>>> 2e34a32cc3d864fb3c9d28b33b5bb1c392685ae7:src/test/java/by/itechart/flowerty/web/controller/TestUserController.java
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
	}    */

    @Ignore
    @Test
    public void getPage_PassValidPageNumber_ShouldReturnLisUserOnThisPage() {
        // TODO: need implements this test
    }
}
