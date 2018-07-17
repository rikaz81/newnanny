package com.go.nanny.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.memorynotfound.config.WebConfig;
//import com.memorynotfound.controller.UserController;
//import com.memorynotfound.filter.CORSFilter;
//import com.memorynotfound.model.User;
//import com.memorynotfound.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.go.nanny.GoNannyApiAppBoot;
import com.go.nanny.model.Agent;
import com.go.nanny.services.AgentService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
// @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GoNannyApiAppBoot.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AgentControllerUnitTest {
	// private MockMvc mockMvc;
	//
	// @Autowired
	// private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Mock
	private AgentService userService;

	@InjectMocks
	private AgentController userController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).addFilters().build();
	}

	// =========================================== Get User By ID
	// =========================================

	@Test
	public void test_get_by_id_success() throws Exception {
		Agent user = new Agent("1", "NAG01", "Unique Service", "Labour & Nanny findings");

		when(userService.getAgentById("1")).thenReturn(user);

		mockMvc.perform(get("/agent/{id}", "1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.agentId", is("1"))).andExpect(jsonPath("$.agentCode", is("NAG01")));

		verify(userService, times(1)).getAgentById("1");
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void test_get_by_id_fail_404_not_found() throws Exception {
		when(userService.getAgentById("2")).thenReturn(null);

		mockMvc.perform(get("/agent/{id}", "2")).andExpect(status().isNotFound());

		verify(userService, times(1)).getAgentById("2");
		verifyNoMoreInteractions(userService);
	}

	// =========================================== Create New User
	// ========================================

	@Test
	public void test_create_user_success() throws Exception {
		Agent user = new Agent("1", "NAG01", "Unique Service", "Labour & Nanny findings");

		when(userService.exists(user)).thenReturn(false);
		doNothing().when(userService).addAgent(user);

		mockMvc.perform(post("/agent/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk()).andExpect(header().string("location", containsString("/agent/1")));

		verify(userService, times(1)).exists(user);
		verify(userService, times(1)).addAgent(user);
		verifyNoMoreInteractions(userService);

		/* $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ */

	}

	@Test
	public void test_create_user_fail_404_not_found() throws Exception {
		Agent user = new Agent("1", "NAG01", "Unique Service", "Labour & Nanny findings");

		when(userService.exists(user)).thenReturn(true);

		mockMvc.perform(post("/agent/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isConflict());

		verify(userService, times(1)).exists(user);
		verifyNoMoreInteractions(userService);
	}

	// =========================================== CORS Headers
	// ===========================================

	@Test
	public void test_cors_headers() throws Exception {
		mockMvc.perform(get("/users")).andExpect(header().string("Access-Control-Allow-Origin", "*"))
				.andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
				.andExpect(header().string("Access-Control-Allow-Headers", "*"))
				.andExpect(header().string("Access-Control-Max-Age", "3600"));
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// @Before
	// public void setup() {
	// this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	//
	// }
	//
	// @Test
	// public void verifyAllAgents() throws Exception {
	// mockMvc.perform(MockMvcRequestBuilders.get("/agent/list").accept(MediaType.APPLICATION_JSON))
	// .andExpect(jsonPath("$", hasSize(3))).andDo(print());
	// }
	//
	// @Test
	// public void verifyAgentById() throws Exception {
	//
	//// mockMvc.perform(MockMvcRequestBuilders.get("/agent/0022").accept(MediaType.APPLICATION_JSON)).andDo(print());
	//
	//
	// MvcResult result = mockMvc.perform(MockMvcRequestBuilders
	// .get("/agent/0022").accept(MediaType.APPLICATION_JSON)).andReturn();
	//
	// String content = result.getResponse().getContentAsString();
	//
	// System.out.println(content);
	//
	// //
	// mockMvc.perform(MockMvcRequestBuilders.get("/agent/0022").accept(MediaType.APPLICATION_JSON))
	// //
	// .andExpect(jsonPath("$.agentId").exists()).andExpect(jsonPath("$.agentId").value(0012)).andDo(print());
	//
	// //
	// mockMvc.perform(MockMvcRequestBuilders.get("/agent/0022").accept(MediaType.APPLICATION_JSON))
	// //
	// .andExpect(jsonPath("$.agentId").exists()).andExpect(jsonPath("$.agentCode").exists())
	// //
	// .andExpect(jsonPath("$.agentName").exists()).andExpect(jsonPath("$.agentId").value(0012))
	// // .andExpect(jsonPath("$.agentCode").value("My agent Code
	// ")).andDo(print());
	//
	// // "agentId": "0012",
	// // "agentCode": "My agent Code ",
	// // "agentName": "My Agent ",
	// // "description": null
	//
	//
	//
	//// verify(userService, times(1)).getAll();
	//// verifyNoMoreInteractions(userService);
	// }
}
