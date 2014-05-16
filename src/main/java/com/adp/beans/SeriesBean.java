package com.adp.beans;

import org.codehaus.jackson.annotate.JsonProperty;

public class SeriesBean {
	@JsonProperty("name")
	private String name;
	@JsonProperty("color")
	private String color;
	@JsonProperty("data")
	private int[] data;

	public SeriesBean(String name, String color, int[] data) {
		this.name = name;
		this.color = color;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}
}
