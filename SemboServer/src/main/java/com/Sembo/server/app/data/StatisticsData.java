package com.Sembo.server.app.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatisticsData implements Serializable {

	private static final long serialVersionUID = 3732226321240731205L;
	
	private String country;
	private double average;
	private List<String> top;

	
	public StatisticsData() {
		this.top = new ArrayList<String>();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public List<String> getTop() {
		return top;
	}

	public void setTop(List<String> top) {
		this.top = top;
	}

}
