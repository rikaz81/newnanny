package com.go.nanny.controller;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.WebApplicationContext;

import com.go.nanny.GoNannyApiAppBoot;
import com.go.nanny.model.Agent;
import com.go.nanny.services.AgentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GoNannyApiAppBoot.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AgentControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyAllAgents() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/agent/list").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(3))).andDo(print());
	}

	@Test
	public void verifyAgentById() throws Exception {

//		mockMvc.perform(MockMvcRequestBuilders.get("/agent/0022").accept(MediaType.APPLICATION_JSON)).andDo(print());
		
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
		         .get("/agent/0022").accept(MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();
		
		System.out.println(content);
		
		// mockMvc.perform(MockMvcRequestBuilders.get("/agent/0022").accept(MediaType.APPLICATION_JSON))
		// .andExpect(jsonPath("$.agentId").exists()).andExpect(jsonPath("$.agentId").value(0012)).andDo(print());

		// mockMvc.perform(MockMvcRequestBuilders.get("/agent/0022").accept(MediaType.APPLICATION_JSON))
		// .andExpect(jsonPath("$.agentId").exists()).andExpect(jsonPath("$.agentCode").exists())
		// .andExpect(jsonPath("$.agentName").exists()).andExpect(jsonPath("$.agentId").value(0012))
		// .andExpect(jsonPath("$.agentCode").value("My agent Code ")).andDo(print());

		// "agentId": "0012",
		// "agentCode": "My agent Code ",
		// "agentName": "My Agent ",
		// "description": null
		
		

//        verify(userService, times(1)).getAll();
//        verifyNoMoreInteractions(userService);
	}
}
