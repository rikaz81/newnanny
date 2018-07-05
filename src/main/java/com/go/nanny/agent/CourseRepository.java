package com.go.nanny.agent;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Agent, String> {

//	public List<Agent> findCourseByTopicTopicId(String topicId);

}
