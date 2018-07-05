package com.go.nanny.agent;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Agent {

	@Id
	private String agentId;

	private String agentCode;

	private String agentName;

	private String description;

	public Agent() {

	}

	public Agent(String agentId, String agentCode, String agentName, String description) {
		super();
		this.agentId = agentId;
		this.agentCode = agentCode;
		this.agentName = agentName;
		this.description = description;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
