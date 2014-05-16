package com.adp.linkedin.schema;

import com.google.gson.Gson;

public class Person {
	public String id;
	public String firstName;
	public String lastName;
	public String headline;
	public String industry;
	public Long numConnections;
	public String summary;
	public String publicProfileUrl;
	public Connections connections;
	public Location location;

	
	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getHeadline() {
		return headline;
	}
	
	public Location getLocation() {
		return location;
	}

	public String getIndustry() {
		return industry;
	}

	public Connections getConnections() {
		return connections;
	}
	
	public Long getNumConnections() {
		return numConnections;
	}

	
	public String getSummary() {
		return summary;
	}
	
	public String getPublicProfileUrl() {
		return publicProfileUrl;
	}

	public boolean isPrivate() {
		if (id != null && id.equals("private"))
			return true;
		return false;
	}

	// Parsing
	public static Person fromJson(String json, Gson gson_) {
		Gson gson = gson_;
		if (gson == null)
			gson = new Gson();
		return gson.fromJson(json, Person.class);
	}

	public static Person fromJson(String json) {
		return fromJson(json, null);
	}
}
