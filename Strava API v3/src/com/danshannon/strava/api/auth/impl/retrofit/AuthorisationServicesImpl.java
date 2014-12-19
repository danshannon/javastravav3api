package com.danshannon.strava.api.auth.impl.retrofit;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.danshannon.strava.api.auth.AuthorisationServices;
import com.danshannon.strava.api.auth.model.TokenResponse;
import com.danshannon.strava.api.auth.ref.AuthorisationApprovalPrompt;
import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;
import com.danshannon.strava.api.auth.ref.AuthorisationScope;
import com.danshannon.strava.api.service.Strava;

/**
 * @author Dan Shannon
 *
 */
public class AuthorisationServicesImpl implements AuthorisationServices {
	private CloseableHttpClient httpClient;
	private BasicCookieStore cookieStore;
	
	public AuthorisationServicesImpl() {
		this.cookieStore = new BasicCookieStore();
		this.httpClient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();


	}

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#tokenExchange(java.lang.Integer, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public TokenResponse tokenExchange(Integer clientId, String clientSecret, String code) {
		// TODO Not yet implemented
		return null;
	}

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#deauthorise(java.lang.String)
	 */
	@Override
	public TokenResponse deauthorise(String accessToken) {
		// TODO Not yet implemented
		return null;
	}
	
	private void get(String uri, NameValuePair... parameters) throws IOException {
		HttpUriRequest get = RequestBuilder.get(uri).addParameters(parameters).build();
		CloseableHttpResponse response = this.httpClient.execute(get);
		try {
			System.out.println(get.getURI() + " " + response);
			for (Cookie cookie : cookieStore.getCookies()) {
				System.out.println(cookie);
			}
			HttpEntity entity = response.getEntity();
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}

	}

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#requestAccess(Integer, String, AuthorisationResponseType,
	 *      AuthorisationApprovalPrompt, AuthorisationScope[], String)
	 */
	@Override
	public void requestAccess(Integer clientId, String redirectURI, AuthorisationResponseType responseType,
			AuthorisationApprovalPrompt approvalPrompt, AuthorisationScope[] scope, String state) throws IOException {
		get(Strava.AUTH_ENDPOINT + "/oauth/authorize",
				new BasicNameValuePair("client_id", clientId.toString()),
				new BasicNameValuePair("response_type", responseType.toString()),
				new BasicNameValuePair("redirect_uri", redirectURI),
				new BasicNameValuePair("approval_prompt", approvalPrompt.toString()) //,
//				(state == null ? null : new BasicNameValuePair("state", state)),
//				(scope == null ? null : new BasicNameValuePair("scope", scope.toString()))
//							+ "?client_id=" + clientId 
//							+ "&response_type=" + responseType 
//							+ "&redirect_uri=" + redirectURI 
//							+ "&approval_prompt=" + approvalPrompt
//							+ (state == null ? "" : "&state=" + state)
//							+ (scope == null ? "" : "&scope=" + scope)
							);
		
		// Get the dashboard page
		get(Strava.AUTH_ENDPOINT + "/dashboard");
	}

	/**
	 * @see com.danshannon.strava.api.auth.AuthorisationServices#login(String, String)
	 */
	@Override
	public void login(String email, String password) {
		try {
			HttpUriRequest login = RequestBuilder.post()
					.setUri(new URI(Strava.AUTH_ENDPOINT + "/session"))
					.addParameter("email", email)
					.addParameter("password", password)
					.build();
			CloseableHttpResponse response2 = httpClient.execute(login);
			try {
				HttpEntity entity = response2.getEntity();
				EntityUtils.consume(entity);
				for (Cookie cookie : cookieStore.getCookies()) {
					System.out.println(cookie);
				}

			} finally {
				response2.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get the login page
		try {
			get(Strava.AUTH_ENDPOINT + "/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String acceptApplication(Integer clientId, String redirectURI, AuthorisationResponseType responseType) {
		// TODO Not yet implemented
		return null;

	}

	@Override
	public void rejectApplication(Integer clientId, String redirectURI, AuthorisationResponseType responseType) {
		// TODO Not yet implemented
	}

}
