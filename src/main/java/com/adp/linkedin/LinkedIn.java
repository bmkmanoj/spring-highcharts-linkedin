package com.adp.linkedin;

import java.util.Set;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.adp.linkedin.schema.Connections;
import com.adp.linkedin.schema.Person;
import com.adp.linkedin.utils.Urls;
import com.google.gson.Gson;

public class LinkedIn {
	//
	// Init
	//

	OAuthService service;
	Token accessToken;
	Gson gson;

	public LinkedIn(String consumerKey, String consumerSecret) {
		init(consumerKey, consumerSecret, null, null);
	}

	public LinkedIn(String consumerKey, String consumerSecret,
			String accessToken, String accessSecret) {
		init(consumerKey, consumerSecret, accessToken, accessSecret);
	}

	public void setAccessToken(String token, String secret) {
		this.accessToken = new Token(token, secret);
	}

	private void init(String consumerKey, String consumerSecret,
			String accessKey, String accessSecret) {
		gson = new Gson();

		service = new ServiceBuilder().provider(LinkedInApi.class)
				.apiKey(consumerKey).apiSecret(consumerSecret).build();

		if (accessKey != null && accessSecret != null)
			setAccessToken(accessKey, accessSecret);
	}

	//
	// Get Requests
	//

	public String getResource(String url, boolean useXML) {
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		if (!useXML)
			request.addHeader("x-li-format", "json");
		service.signRequest(accessToken, request);
		try {
			Response response = request.send();
			return response.getBody();
		} catch (RuntimeException er) {

		}
		return null;
	}

	public String getResource(String url) throws RuntimeException {
		return getResource(url, false);
	}

	//
	// Person Api
	//

	/**
	 * Gets the profile for current user. For details see <a
	 * href="http://developer.linkedin.com/docs/DOC-1002"
	 * >http://developer.linkedin.com/docs/DOC-1002</a>
	 * 
	 * @return the profile for current user
	 */
	public Person getProfileForCurrentUser() {
		return Person.fromJson(getResource(Urls.PERSON_URL_SELF), gson);
	}

	/**
	 * Gets the profile for the url like: https://api.linkedin.com/v1/people/~
	 * For details see <a
	 * href="http://developer.linkedin.com/docs/DOC-1002">http
	 * ://developer.linkedin.com/docs/DOC-1002</a>
	 * 
	 * @return the profile for current user
	 */
	public Person getProfileForUrl(String url) {
		return Person.fromJson(getResource(url), gson);
	}

	//
	// Connections API
	//

	/**
	 * Gets the connections for current user. For details see <a
	 * href="http://developer.linkedin.com/docs/DOC-1004"
	 * >http://developer.linkedin.com/docs/DOC-1004</a>
	 * 
	 * @return the connections for current user
	 */
	public Connections getConnectionsForCurrentUser() {
		return Connections.fromJson(
				getResource(Urls.PERSON_CONNECTIONS_URL_SELF), gson);
	}
}
