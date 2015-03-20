package test.by.itecharty.flowerty.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import test.by.itecharty.flowerty.config.WebAppConfigurationAware;

public class HomeControllerTest extends WebAppConfigurationAware  {
    @Test
    public void testGetContactList() throws Exception{
	ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/list").accept(MediaType.APPLICATION_JSON));
	resultActions.andDo(MockMvcResultHandlers.print());
	String content = resultActions.andReturn().getResponse().getContentAsString();
	System.out.println(content);
    }
}
