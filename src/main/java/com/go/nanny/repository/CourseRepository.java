package com.go.nanny.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.go.nanny.model.Agent;

public interface CourseRepository extends CrudRepository<Agent, String> {

	// public List<Agent> findCourseByTopicTopicId(String topicId);
	public Agent findAgentByAgentName(String agentName);

}
