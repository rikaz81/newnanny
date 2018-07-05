package com.go.nanny.agent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	// @RequestMapping("/topics/{topicid}/courses")
	// public List<Course> getAllCourses(@PathVariable("topicid") String topicId) {
	//
	// return courseService.getAllCourses(topicId);
	//
	// }
	//
	// @RequestMapping("/topics/{topicid}/courses/{id}")
	// public Course getCourses(@PathVariable("id") String courseId) {
	//
	// return courseService.getCourse(courseId);
	//
	// }
	//
	// @RequestMapping(method = RequestMethod.POST, value =
	// "/topics/{topicid}/courses")
	// public void addTopic(@RequestBody Course course, @PathVariable("topicid")
	// String topicId) {
	// course.setTopic(new Topic(topicId, "", ""));
	// courseService.addCourse(course);
	// }
	//
	// @RequestMapping(method = RequestMethod.PUT, value =
	// "/topics/{topicid}/courses/{id}")
	// public void updateTopic(@RequestBody Course course, @PathVariable("id")
	// String courseId) {
	// courseService.updateCourse(courseId, course);
	// }
	//
	// @RequestMapping(method = RequestMethod.DELETE, value =
	// "/topics/{topicid}/courses/{id}")
	// public void deleteTopic(@PathVariable("id") String courseId) {
	// courseService.deleteCourse(courseId);
	// }
}
