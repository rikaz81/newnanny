package com.go.nanny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.go.nanny.model.Agent;
import com.go.nanny.services.AgentService;

@RestController
public class AgentController {

	@Autowired
	private AgentService courseService;

	@RequestMapping(method = RequestMethod.POST, value = "/agent/register")
	public void addTopic(@RequestBody Agent agent) {
		// course.setTopic(new Topic(topicId, "", ""));
		courseService.addAgent(agent);
	}

	@RequestMapping("/agent/list")
	public List<Agent> getAllAgents() {

		return courseService.getAllAgents();

	}

	@RequestMapping("/agent/{id}")
	public Agent getAgentById(@PathVariable("id") String courseId) {

		return courseService.getAgentById(courseId);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/agent/edit/{id}")
	public void updateAgent(@RequestBody Agent course, @PathVariable("id") String courseId) {
		courseService.updateAgent(courseId, course);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/agent/{id}")
	public void deleteTopic(@PathVariable("id") String courseId) {
		courseService.deleteCourse(courseId);
	}

}
