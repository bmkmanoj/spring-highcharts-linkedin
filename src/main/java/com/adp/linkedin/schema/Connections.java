package com.adp.linkedin.schema;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Connections {
	@SerializedName("_total")
	public long total;
	public List<Person> values;
	
	public List<Person> getPersonList() {
		return values;
	}

	public long getTotal() {
		return total;
	}

	// Parsing
	public static Connections fromJson(String json, Gson gson_) {
		Gson gson = gson_;
		if (gson == null)
			gson = new Gson();
		return gson.fromJson(json, Connections.class);
	}

}
