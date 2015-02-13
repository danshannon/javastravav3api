package stravajava.api.v3.service.impl.retrofit;

import java.io.IOException;

import lombok.extern.log4j.Log4j2;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;
import stravajava.api.v3.model.StravaResponse;
import stravajava.api.v3.service.exception.BadRequestException;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.StravaAPIRateLimitException;
import stravajava.api.v3.service.exception.StravaUnknownAPIException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.JsonUtilImpl;

/**
 * @author dshannon
 *
 */
@Log4j2
public class RetrofitErrorHandler implements ErrorHandler {
	JsonUtil json = new JsonUtilImpl();

	/**
	 * @see retrofit.ErrorHandler#handleError(retrofit.RetrofitError)
	 */
	@Override
	public Throwable handleError(final RetrofitError cause) {
		Response r = cause.getResponse();
		StravaResponse response = null;
		String status = (r == null ? "Unknown error has occurred" : r.getStatus() + " " + r.getReason());
		try {
			response = this.json.deserialise(r.getBody().in(),StravaResponse.class);
		} catch (JsonSerialisationException e) {
			response = new StravaResponse();
			response.setMessage(r.getBody().toString());
		} catch (IOException e) {
			response = new StravaResponse();
			response.setMessage(r.getBody().toString());
		}

		// Handle 400 Bad request error
		if (r != null && r.getStatus() == 400) {
			log.warn(status + " : " + response);
			return new BadRequestException(status,response, cause);
		}

		// Handle 401 Unauthorized error
		if (r != null && r.getStatus() == 401) {
			log.warn(status + " : " + response);
			return new UnauthorizedException(status, response, cause);
		}
		
		// Handle 403 forbidden error
		if (r != null && r.getStatus() == 403) {
			log.error(status + " : " + response);
			if (response.getMessage() == "Rate Limit Exceeded") {
				return new StravaAPIRateLimitException(status, response, cause);
			}
		}

		// Handle 404 Not Found error
		if (r != null && r.getStatus() == 404) {
			log.info(status + " : " + response);
			return new NotFoundException(response,cause);
		}
		
		log.error(response);
		return new StravaUnknownAPIException(status,response,cause);
	}

}
