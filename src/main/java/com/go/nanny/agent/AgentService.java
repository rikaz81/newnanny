package com.go.nanny.agent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

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

	public Agent getCourse(String topic) {
		return courseRepository.findOne(topic);

	}

	public void addAgent(Agent topic) {
		courseRepository.save(topic);
	}

	public void updateCourse(String id, Agent newTopic) {

		courseRepository.save(newTopic);
	}

	public void deleteCourse(String topicId) {
		courseRepository.delete(topicId);

	}

}
