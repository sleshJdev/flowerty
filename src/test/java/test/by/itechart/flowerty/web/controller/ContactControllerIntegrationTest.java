package test.by.itechart.flowerty.web.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.ResultActions;

import test.by.itechart.flowerty.config.aware.WebApplicationConfigurationAware;
import by.itechart.flowerty.persistence.model.Contact;
import by.itechart.flowerty.persistence.model.User;
/**
 * @author Eugene Putsykovich(slesh) May 16, 2015
 *
 *	integration tests for ContactController
 */
@Ignore
public class ContactControllerIntegrationTest extends WebApplicationConfigurationAware {
    private MockHttpSession session;
    
    @Before
    public void setup(){
	final User supervisor = HelperTestsController.getSupervisor();
	session = makeAuthSession(supervisor.getLogin());
    }
    
    @Test
    public void page_ShouldReturnPageOfContacts() throws Exception{
	final Integer pageNumber = 1;
	final Integer pageSize = 10;
	final String url = String.format("/contact/list/%d/%d", pageNumber, pageSize);
	
	mockMvc
		.perform(get(url)
				.session(session)
			)
		.andExpect(status().isOk())
		.andExpect(content().contentType(HelperTestsController.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.content").exists())
		.andExpect(jsonPath("$.content").isArray())
		.andExpect(jsonPath("$.content", hasSize(lessThanOrEqualTo(pageSize))))
		.andExpect(jsonPath("$.number").value(pageNumber - 1))
		;
    }
    
    @Test
    public void details_PassValidId_ShouldReturnContactInstance() throws Exception{
	final Contact expected = HelperTestsController.getContactWithIdOne();
	System.out.println(expected);
	final String url = String.format("/contact/details/%d", expected.getId());
	
	ResultActions result = mockMvc
		.perform(get(url)
				.session(session)
			)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").exists())
		.andExpect(jsonPath("$.id").value(Integer.valueOf(expected.getId().toString())))
		.andExpect(jsonPath("$.name").value(expected.getName()))
		.andExpect(jsonPath("$.surname").value(expected.getSurname()))
		.andExpect(jsonPath("$.fathername").value(expected.getFathername()))
		;
	
	System.out.println("::: " + result.andReturn().getResponse().getContentAsString());
    }
    
    @Test
    public void details_PassInvalidId_ShouldReturnNull() throws Exception{
	final Long id = Long.MAX_VALUE;
	final String url = String.format("/contact/details/%d", id);
	
	mockMvc
		.perform(get(url)
				.session(session)
			)
		.andExpect(status().isOk())
		;
    }
    
    @Test
    public void remove_PassValidIdOfContact_ShoudDeleteIt() throws Exception{
	final String url = "/contact/remove";
	
	mockMvc
		.perform(post(url)
				.session(session)
				.contentType(HelperTestsController.APPLICATION_JSON_UTF8)
				.content(HelperTestsController.convertObjectToJsonBytes(Arrays.asList(HelperTestsController.getContactForRemoving())))
			)
		.andExpect(status().isOk())
		;
	//TODO: add asserts
    }
    
    @Ignore
    @Test
    public void save_PassValidContact_ShouldSaveIt() throws Exception{
	final String url = "/contact/save";
	
	mockMvc
        	.perform(post(url)
        			.session(session)
        			.contentType(HelperTestsController.APPLICATION_JSON_UTF8)
        			.content(HelperTestsController.convertObjectToJsonBytes(HelperTestsController.getContactForSaving()))
        		)
        	.andExpect(status().isOk())
        	;
    }
    
    @Ignore
    @Test
    public void searchBySurname_PassFullSurnameName_ShoudReturnCollectionWithSizeOneWhichContainsContact() throws Exception{
	//TODO: add unit test for this case, to avoid solr dependencies!!!!!!!

	Contact expected = HelperTestsController.getContactWithIdOne();
	final String url = String.format("/contact/partial-search/%s", expected.getSurname());
	
	mockMvc
		.perform(get(url).session(session))
		.andExpect(content().contentType(HelperTestsController.APPLICATION_JSON_UTF8))
		;
    }
}
