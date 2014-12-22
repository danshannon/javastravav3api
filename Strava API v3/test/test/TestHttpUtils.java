package test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.Header;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;
import com.danshannon.strava.api.service.Strava;

/**
 * @author dshannon
 *
 */


public class TestHttpUtils {
	private CloseableHttpClient httpClient;
	private BasicCookieStore cookieStore;
	
	public TestHttpUtils() {
		this.cookieStore = new BasicCookieStore();
		this.httpClient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
	}

	public Document get(String uri, NameValuePair... parameters) throws IOException {
		HttpUriRequest get = null;
		Document page = null;
		if (parameters == null) {
			get = RequestBuilder.get(uri).build();
		} else {
		get = RequestBuilder.get(uri).addParameters(parameters).build();
		}
		CloseableHttpResponse response = this.httpClient.execute(get);
		try {
			System.out.println(get.getURI() + " " + response);
			for (Cookie cookie : cookieStore.getCookies()) {
				System.out.println(cookie);
			}
			HttpEntity entity = response.getEntity();
			page = Jsoup.parse(EntityUtils.toString(entity));
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}

		return page;
	}

	/**
	 * <p>Login to the Strava application</p>
	 * 
	 * <p>This method is provided FOR TESTING PURPOSES ONLY as it's not genuinely useful and you shouldn't be asking other people for their Strava password</p>
	 * 
	 * <p>URL POST https://www.strava.com/session</p>
	 * 
	 * TODO Session cookie?
	 * 
	 * @param email Email address associated with the user account
	 * @param password Password associated with the user account
	 * @param authenticityToken token handed out by the Strava login page within the login form
	 * @return The string URL to redirect to next
	 */
	public String login(String email, String password, String authenticityToken) {
		String location = null;
		try {
			HttpUriRequest login = RequestBuilder.post()
					.setUri(new URI(Strava.AUTH_ENDPOINT + "/session"))
					.addParameter("email", email)
					.addParameter("password", password)
					.addParameter("authenticity_token",authenticityToken)
					.addParameter("utf8","✓")
					.build();
			CloseableHttpResponse response2 = httpClient.execute(login);
			try {
				HttpEntity entity = response2.getEntity();
				location = response2.getFirstHeader("Location").getValue();
				System.out.println(login.getMethod() + " " + login.getURI() + " " + response2.getStatusLine());
				System.out.println(EntityUtils.toString(entity));
				for (Header header : response2.getAllHeaders()) {
					System.out.println(header);
				}
				for (Cookie cookie : cookieStore.getCookies()) {
					System.out.println(cookie);
				}
				EntityUtils.consume(entity);

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
		
		return location;

	}
	/**
	 * <p>Indicate that the user has allowed the application to access their Strava data</p>
	 * 
	 * <p>This method is provided FOR TESTING PURPOSES ONLY</p>
	 * 
	 * @param clientId The application's ID, obtained during registration
	 * @param redirectURI URI to which a redirect should be issued
	 * @param responseType must be "code"
	 * @return The code used by {@link #tokenExchange(Integer, String, String)} to get an access token
	 */
	public String acceptApplication(Integer clientId, String redirectURI, AuthorisationResponseType responseType) {
		// TODO Not yet implemented
		return null;
	}
	
	/**
	 * <p>Indicate that the user has DENIED the application access to their Strava data</p>
	 * 
	 * <p>This method is provided FOR TESTING PURPOSES ONLY</p>
	 * 
	 * @param clientId The application's ID, obtained during registration
	 * @param redirectURI URI to which a redirect should be issued
	 * @param responseType must be "code"
	 */
	public void rejectApplication(Integer clientId, String redirectURI, AuthorisationResponseType responseType){
		// TODO Not yet implemented
		return;
	}

	/**
	 * <p>Get the login page and extract the authenticity token that Strava cunningly hides in the login form</p>
	 * @return The value of the authenticity token, which should be included when posting the form to log in
	 * @throws IOException 
	 */
	public String getLoginAuthenticityToken() throws IOException {
		BasicNameValuePair[] params = null;
		Document loginPage = get(Strava.AUTH_ENDPOINT + "/login", params);
		Elements authTokens = loginPage.select("input[name=authenticity_token]");
		for (Element element : authTokens) {
			System.out.println("Element = " + element.toString());
		}
		return authTokens.first().attr("value");
	}

}
