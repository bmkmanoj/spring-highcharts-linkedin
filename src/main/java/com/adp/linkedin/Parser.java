package com.adp.linkedin;

/**
 * Parsing Utility Helper class.
 *
 */

import com.google.gson.Gson;

public class Parser {
	public static <T> T fromJson(String json, Gson gson_, Class<T> classe) {
		Gson gson = gson_;
		if (gson == null)
			gson = new Gson();
		try {
			return gson.fromJson(json, classe);
		} catch (Exception er) {

		}
		return null;
	}
}
