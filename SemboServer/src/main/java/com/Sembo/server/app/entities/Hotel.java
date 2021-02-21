package com.Sembo.server.app.entities;

import java.io.Serializable;

public class Hotel implements Serializable {

	private static final long serialVersionUID = 5110702765485486189L;
	
	private String id;
	private String name;
	private String isoCountryId;
	private Integer score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsoCountryId() {
		return isoCountryId;
	}

	public void setIsoCountryId(String isoCountryId) {
		this.isoCountryId = isoCountryId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
