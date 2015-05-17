package test.by.itechart.flowerty.web.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MockMvc;

import by.itechart.flowerty.persistence.model.Contact;
import by.itechart.flowerty.persistence.model.User;
import test.by.itechart.flowerty.config.aware.WebApplicationConfigurationAware;
/**
 * @author Eugene Putsykovich(slesh) May 16, 2015
 *
 *	tests for ContactController
 */
public class ContactControllerTests extends WebApplicationConfigurationAware {
    @Test
    public void page_ShouldReturnPageOfContacts() throws Exception{
	final int pageNumber = 1;
	final int pageSize = 10;
	final String url = String.format("/contact/list/%d/%d", pageNumber, pageSize);
	
	mockMvc
		.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(content().contentType(HelperTestsController.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.content").exists())
		.andExpect(jsonPath("$.content").isArray())
		.andExpect(jsonPath("$.content", hasSize(lessThanOrEqualTo(pageSize))))
		;
    }
    
    @Test
    public void searchBySurname_PassFullSurnameName_ShoudReturnCollectionWithSizeOneWhichContainsContact() throws Exception{
	final Contact expected = HelperTestsController.getContactWithIdOne();
	final String url = String.format("/contact/partial-search/%s", expected.getSurname());
	
	final User supervisor = HelperTestsController.getUserWhichHasPermissionToUsePartialSearch();
	
	UsernamePasswordAuthenticationToken principal = getPrincipal(supervisor.getPassword());
	MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, 
                new MockSecurityContext(principal));
	
	mockMvc
		.perform(get(url))
		.andExpect(content().contentType(HelperTestsController.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.content").exists())
		.andExpect(jsonPath("$.content").isArray())
		.andExpect(jsonPath("$.content", hasSize(1)))
		.andExpect(jsonPath("$.content[1].id").value(expected.getId()))
		.andExpect(jsonPath("$.content[1].name").value(expected.getName()))
		;
    }
}
