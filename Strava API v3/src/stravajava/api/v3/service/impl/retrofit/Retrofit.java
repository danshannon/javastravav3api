package stravajava.api.v3.service.impl.retrofit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import stravajava.api.v3.service.Strava;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
public class Retrofit {
	private Retrofit() {
		// You can't have one of these
	}

	public static <T> T retrofit(final Class<T> class1, final String token, final RestAdapter.LogLevel logLevel) {
		return new RestAdapter.Builder().setConverter(new GsonConverter(new JsonUtilImpl().getGson())).setLogLevel(logLevel).setEndpoint(Strava.ENDPOINT)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addHeader("Authorization", "Bearer " + token);
					}
				}).setErrorHandler(new RetrofitErrorHandler()).build().create(class1);
	}

}
