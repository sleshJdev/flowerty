package by.itecharty.flowerty.web.controller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.exception.NotFoundException;
import by.itecharty.flowerty.config.WebAppConfigurationAware;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         Test for UserController
 */
public class UserControllerTest extends WebAppConfigurationAware {
    @Autowired
    private static UserRepository userServiceMock;

    @Before
    public void setUp(){
	
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void getById_PassNotValidUserId_ShouldReturnHttpStatusCode404() throws Exception {
	final Long id = -1L;
	
	when(userServiceMock.findOne(id))
		.thenThrow(NotFoundException.class);

	mockMvc.perform(get("/user/details/{id}", id))
		.andExpect(status().isBadRequest());

	
	verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void getById_PassValidUserId_ShouldReturnExistsUser() throws Exception{
	final Long id = 1L;
	final String login = "slesh";
	final String password = "gtx260";
	
	User returnedUser = new User();
	returnedUser.setId(id);
	returnedUser.setLogin(login);
	returnedUser.setPassword(password);
	
	when(userServiceMock.findOne(id))
		.thenReturn(returnedUser);
	
	mockMvc.perform(get("/user/details/{id}", id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.login", is(login)))
		.andExpect(jsonPath("$.password", is(password)));

	verifyNoMoreInteractions(userServiceMock);
    }
    
    @Test
    public void add_PassValidJson_ShouldReturnCreatedUserObject() {
	final Long id = 1L;
	final String login = "slesh";
	final String password = "gtx260";

	User returnedUser = new User();
	returnedUser.setId(id);
	returnedUser.setLogin(login);
	returnedUser.setPassword(password);

	
	// ???????
    }
}
