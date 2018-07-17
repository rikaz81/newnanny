package com.go.nanny.services;

import java.util.List;

import com.go.nanny.model.Agent;

public interface AgentService {

	public List<Agent> getAllAgents();

	public Agent findAgentByAgentName(String agentName);

	public Agent getAgentById(String topic);

	public Agent addAgent(Agent topic);

	public void updateAgent(String id, Agent newTopic);

	public void deleteCourse(String topicId);

	public boolean exists(Agent user);
}
