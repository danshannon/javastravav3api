package javastrava.api.v3.service.impl.retrofit;

import java.io.File;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaUploadResponse;
import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.service.UploadServices;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.config.Messages;
import retrofit.mime.TypedFile;

/**
 * <p>
 * Implementation of {@link UploadServices}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class UploadServicesImpl extends StravaServiceImpl<UploadServicesRetrofit> implements UploadServices {

	/**
	 * <p>
	 * Private constructor prevents anyone getting an instance without going via {@link #implementation(Token)}
	 * </p>
	 * 
	 * @param token The access token used to authenticate to the Strava API
	 */
	private UploadServicesImpl(final Token token) {
		super(UploadServicesRetrofit.class, token);
	}

	/**
	 * <p>
	 * Returns an implementation of {@link UploadServices segment effort services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An implementation of the upload services
	 */
	public static UploadServices implementation(final Token token) {
		// Get the service from the token's cache
		UploadServices service = token.getService(UploadServices.class);
		
		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new UploadServicesImpl(token);
			token.addService(UploadServices.class, service);
		}
		return service;
	}

	/**
	 * @see javastrava.api.v3.service.UploadServices#upload(javastrava.api.v3.model.reference.StravaActivityType, java.lang.String, java.lang.String,
	 *      java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.io.File)
	 */
	@Override
	public StravaUploadResponse upload(final StravaActivityType activityType, final String name, final String description, final Boolean _private,
			final Boolean trainer, final String dataType, final String externalId, final File file) {
		if (file == null) {
			throw new IllegalArgumentException(Messages.string("UploadServicesImpl.cannotUploadNullFile")); //$NON-NLS-1$
		}
		if (!file.exists() || file.isDirectory()) {
			throw new IllegalArgumentException(String.format(Messages.string("UploadServicesImpl.fileDoesNotExist"), file.getName())); //$NON-NLS-1$
		}
		try {
			return this.restService.upload(activityType, name, description, _private, trainer, dataType, externalId, new TypedFile("text/xml", file)); //$NON-NLS-1$
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see javastrava.api.v3.service.UploadServices#checkUploadStatus(java.lang.Integer)
	 */
	@Override
	public StravaUploadResponse checkUploadStatus(final Integer id) {
		return this.restService.checkUploadStatus(id);
	}

}
