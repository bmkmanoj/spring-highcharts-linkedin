package com.adp.linkedin.schema;

public class Position {
	public String id;
	public String title;
	public String summary;
	public boolean isCurrent;
	public String description;
	public String descriptionSnippet;
	public Location location;

	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public String getSummary() {
		return summary;
	}
	
	public boolean isCurrent() {
		return isCurrent;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptionSnippet() {
		return descriptionSnippet;
	}

	public Location getLocation() {
		return location;
	}
}
