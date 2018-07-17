package com.go.nanny.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.go.nanny.model.Agent;
import com.go.nanny.repository.CourseRepository;

@Service
public class AgentServiceImpl implements AgentService {

	@Autowired
	private CourseRepository courseRepository;

	// public List<Agent> getAllCourses(String topicId) {
	// List<Agent> topics = new ArrayList<>();
	//
	// courseRepository.findCourseByTopicTopicId(topicId).forEach(topics::add);
	// return topics;
	// }

	public List<Agent> getAllAgents() {
		List<Agent> topics = new ArrayList<>();

		courseRepository.findAll().forEach(topics::add);
		return topics;
	}

	public Agent findAgentByAgentName(String agentName) {
		return courseRepository.findAgentByAgentName(agentName);
	}

	public Agent getAgentById(String topic) {
		return courseRepository.findOne(topic);

	}

	public Agent addAgent(Agent topic) {
		return courseRepository.save(topic);
	}

	public void updateAgent(String id, Agent newTopic) {

		courseRepository.save(newTopic);
	}

	public void deleteCourse(String topicId) {
		courseRepository.delete(topicId);

	}

	@Override
	public boolean exists(Agent user) {
		return findAgentByAgentName(user.getAgentName()) != null;
	}

}
