package javastrava.api.v3.rest.util;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javastrava.api.v3.model.StravaAPIError;
import javastrava.api.v3.model.StravaResponse;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.api.v3.service.exception.InvalidTokenException;
import javastrava.api.v3.service.exception.NotFoundException;
import javastrava.api.v3.service.exception.StravaAPINetworkException;
import javastrava.api.v3.service.exception.StravaAPIRateLimitException;
import javastrava.api.v3.service.exception.StravaInternalServerErrorException;
import javastrava.api.v3.service.exception.StravaServiceUnavailableException;
import javastrava.api.v3.service.exception.StravaUnknownAPIException;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.config.Messages;
import javastrava.json.JsonUtil;
import javastrava.json.exception.JsonSerialisationException;
import javastrava.json.impl.gson.JsonUtilImpl;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.RetrofitError.Kind;
import retrofit.client.Response;

/**
 * <p>
 * Interceptor to handle errors thrown by the Strava API.
 * </p>
 * 
 * <p>
 * HTTP error status codes are converted to checked (if recoverable) or unchecked (if not) exceptions and thrown
 * </p>
 * 
 * <p>
 * With each error, Strava also returns a set of error messages which are encapsulated in {@link StravaResponse}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class RetrofitErrorHandler implements ErrorHandler {
	private static final Logger log = LogManager.getLogger();
	JsonUtil json = new JsonUtilImpl();

	/**
	 * @see retrofit.ErrorHandler#handleError(retrofit.RetrofitError)
	 */
	@Override
	public Throwable handleError(final RetrofitError cause) {
		Response r = cause.getResponse();
		StravaResponse response = null;
		String status = (r == null ? Messages.string("RetrofitErrorHandler.unknownError") : r.getStatus() + " " + r.getReason()); //$NON-NLS-1$ //$NON-NLS-2$
		
		// Handle network errors
		if (cause.getKind() == Kind.NETWORK) {
			return new StravaAPINetworkException(null, response, cause);
		}
		
		if (r == null) {
			throw new StravaUnknownAPIException(status, response, cause);
		}
		try {
			if (r.getBody() == null) {
			response = new StravaResponse();
			response.setMessage(cause.getMessage());
			} else {
			response = this.json.deserialise(r.getBody().in(),StravaResponse.class);
			}
		} catch (JsonSerialisationException e) {
			response = new StravaResponse();
			response.setMessage(r.getBody().toString());
		} catch (IOException e) {
			response = new StravaResponse();
			response.setMessage(r.getBody().toString());
		} catch (Exception e) {
			response = new StravaResponse();
			response.setMessage(cause.toString());
		}

		// Handle 400 Bad request error
		if (r != null && r.getStatus() == 400) {
			log.warn(status + " : " + response); //$NON-NLS-1$
			return new BadRequestException(status,response, cause);
		}

		// Handle 401 Unauthorized error
		if (r != null && r.getStatus() == 401) {
			if (tokenInvalid(response)) {
				log.error(status + " : " + response); //$NON-NLS-1$
				return new InvalidTokenException(status, response, cause);
			}
			
			log.warn(status + " : " + response); //$NON-NLS-1$
			return new UnauthorizedException(status, response, cause);
		}
		
		// Handle 403 forbidden error
		if (r != null && r.getStatus() == 403) {
			log.error(status + " : " + response); //$NON-NLS-1$
			if (response.getMessage().equals(Messages.string("RetrofitErrorHandler.rateLimitExceeded"))) { //$NON-NLS-1$
				return new StravaAPIRateLimitException(status, response, cause);
			} else {
				return new UnauthorizedException(status, response, cause);
			}
		}

		// Handle 404 Not Found error
		if (r != null && r.getStatus() == 404) {
			log.info(status + " : " + response); //$NON-NLS-1$
			return new NotFoundException(response,cause);
		}
		
		// Handle 500 Internal Server error
		if (r != null && r.getStatus() == 500) {
			log.error(status + " : " + response); //$NON-NLS-1$
			return new StravaInternalServerErrorException(status, response, cause);
		}
		
		// Handle 503 Service Unavailable error
		if (r != null && r.getStatus() == 503) {
			log.error(status + " : " + response); //$NON-NLS-1$
			return new StravaServiceUnavailableException(status, response, cause);
		}
		
		log.error(response);
		return new StravaUnknownAPIException(status,response,cause);
	}

	private static boolean tokenInvalid(final StravaResponse response) {
		if (response == null || response.getErrors() == null || response.getErrors().isEmpty()) {
			return false;
		}
		for (StravaAPIError error : response.getErrors() ) {
			if (error.getResource().equals("Application") && error.getCode().equals("invalid")) {
				return true;
			}
		}
		return false;
	}

}
