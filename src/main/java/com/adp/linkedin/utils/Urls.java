package com.adp.linkedin.utils;

// Urls
//

public class Urls {
	public final static String URL_BASE = "https://api.linkedin.com/v1/";
	public final static String PERSON_URL_SELF = URL_BASE + "people/~";
	public final static String PERSON_URL_ID = URL_BASE + "people/%s";
	public final static String PERSON_CONNECTIONS_URL_SELF = URL_BASE
			+ "people/~/connections";
	public final static String PERSON_CONNECTIONS_URL_ID = URL_BASE
			+ "people/%s/connections";
}
