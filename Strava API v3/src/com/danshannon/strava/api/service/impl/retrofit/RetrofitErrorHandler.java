package com.danshannon.strava.api.service.impl.retrofit;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.danshannon.strava.api.service.exception.BadRequestException;
import com.danshannon.strava.api.service.exception.NotFoundException;
import com.danshannon.strava.api.service.exception.UnauthorizedException;

/**
 * @author dshannon
 *
 */
public class RetrofitErrorHandler implements ErrorHandler {

	/**
	 * @see retrofit.ErrorHandler#handleError(retrofit.RetrofitError)
	 */
	@Override
	public Throwable handleError(RetrofitError cause) {
		Response r = cause.getResponse();
		
		// Handle 400 Bad request error
		if (r != null && r.getStatus() == 400) {
			return new BadRequestException(cause);
		}
		
		// Handle 401 Unauthorized error
		if (r != null && r.getStatus() == 401) {
			return new UnauthorizedException(cause);
		}
		
		// Handle 404 Not Found error
		if (r != null && r.getStatus() == 404) {
			return new NotFoundException(cause);
		}
		
		return cause;
	}

}
