package com.go.nanny.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.go.nanny.model.Agent;
import com.go.nanny.repository.CourseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class AgentServiceTest {

	@Mock
	private CourseRepository courseRepository;

	@InjectMocks
	private AgentServiceImpl toDoService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllAgents() {

		List<Agent> agents = new ArrayList<>();
		agents.add(new Agent("1", "NAG01", "Unique Service", "Labour & Nanny findings"));
		agents.add(new Agent("2", "NAG02", "Find AAyA", "Find AAyA more"));
		agents.add(new Agent("3", "NAG03", "My Nanny Care", "My Nanny Care more"));

		when(courseRepository.findAll()).thenReturn(agents);

		List<Agent> result = toDoService.getAllAgents();
		assertEquals(3, result.size());

	}

	@Test
	public void testGetToDoById() {
		Agent agent = new Agent("1", "NAG01", "Unique Service", "Labour & Nanny findings");
		when(courseRepository.findOne("1")).thenReturn(agent);

		Agent result = toDoService.getAgentById(agent.getAgentId());
		assertEquals("1", result.getAgentId());
		assertEquals("NAG01", result.getAgentCode());
		assertEquals("Unique Service", result.getAgentName());

	}

	@Test
	public void testFindAgentByAgentName() {
		Agent agent = new Agent("3", "NAG03", "My Nanny Care", "My Nanny Care more");
		when(courseRepository.findAgentByAgentName("My Nanny Care")).thenReturn(agent);

		Agent result = toDoService.findAgentByAgentName(agent.getAgentName());

		assertEquals("My Nanny Care", result.getAgentName());
		assertEquals("3", result.getAgentId());
		assertEquals("NAG03", result.getAgentCode());

	}

	@Test
	public void addAgent() {

		Agent agent = new Agent("5", "NAG05", "Peter Take Cares", "Peter at home");
		when(courseRepository.save(agent)).thenReturn(agent);

		Agent result = toDoService.addAgent(agent);
		assertEquals("5", result.getAgentId());
		assertEquals("Peter Take Cares", result.getAgentName());
		assertEquals("NAG05", result.getAgentCode());

	}

}
