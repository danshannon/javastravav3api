package javastrava.api.v3.service.impl;

import java.io.File;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaUploadResponse;
import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.service.UploadService;
import javastrava.api.v3.service.exception.BadRequestException;
import javastrava.config.Messages;
import retrofit.mime.TypedFile;

/**
 * <p>
 * Implementation of {@link UploadService}
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class UploadServiceImpl extends StravaServiceImpl implements UploadService {

	/**
	 * <p>
	 * Private constructor prevents anyone getting an instance without going via {@link #instance(Token)}
	 * </p>
	 * 
	 * @param token The access token used to authenticate to the Strava API
	 */
	private UploadServiceImpl(final Token token) {
		super(token);
	}

	/**
	 * <p>
	 * Returns an instance of {@link UploadService segment effort services}
	 * </p>
	 * 
	 * <p>
	 * Instances are cached so that if 2 requests are made for the same token, the same instance is returned
	 * </p>
	 * 
	 * @param token
	 *            The Strava access token to be used in requests to the Strava API
	 * @return An instance of the upload services
	 */
	public static UploadService instance(final Token token) {
		// Get the service from the token's cache
		UploadService service = token.getService(UploadService.class);
		
		// If it's not already there, create a new one and put it in the token
		if (service == null) {
			service = new UploadServiceImpl(token);
			token.addService(UploadService.class, service);
		}
		return service;
	}

	/**
	 * @see javastrava.api.v3.service.UploadService#upload(javastrava.api.v3.model.reference.StravaActivityType, java.lang.String, java.lang.String,
	 *      java.lang.Boolean, java.lang.Boolean, java.lang.String, java.lang.String, java.io.File)
	 */
	@Override
	public StravaUploadResponse upload(final StravaActivityType activityType, final String name, final String description, final Boolean _private,
			final Boolean trainer, final String dataType, final String externalId, final File file) {
		if (file == null) {
			throw new IllegalArgumentException(Messages.string("UploadServiceImpl.cannotUploadNullFile")); //$NON-NLS-1$
		}
		if (!file.exists() || file.isDirectory()) {
			throw new IllegalArgumentException(String.format(Messages.string("UploadServiceImpl.fileDoesNotExist"), file.getName())); //$NON-NLS-1$
		}
		try {
			return this.api.upload(activityType, name, description, _private, trainer, dataType, externalId, new TypedFile("text/xml", file)); //$NON-NLS-1$
		} catch (BadRequestException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @see javastrava.api.v3.service.UploadService#checkUploadStatus(java.lang.Integer)
	 */
	@Override
	public StravaUploadResponse checkUploadStatus(final Integer id) {
		return this.api.checkUploadStatus(id);
	}

}
