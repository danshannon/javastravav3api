package stravajava.api.v3.service.impl.retrofit;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;
import stravajava.api.v3.service.exception.BadRequestException;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;

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
