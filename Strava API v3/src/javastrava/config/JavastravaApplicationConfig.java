package javastrava.config;

import java.util.ResourceBundle;

/**
 * <p>
 * Permissions given by the nice people at Strava to individual applications.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class JavastravaApplicationConfig {
	/**
	 * Resource bundle containing configuration properties
	 */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("javastrava-application"); //$NON-NLS-1$

	/**
	 * Strava application-level permission to allow activity deletion. Email developers [at] strava.com to get permission, if they're feeling generous.
	 */
	public static final boolean STRAVA_ALLOWS_ACTIVITY_DELETE = Boolean.parseBoolean(RESOURCE_BUNDLE.getString("strava.permission.deleteActivity")); //$NON-NLS-1$

	/**
	 * Strava application-level permission to allow comment creation or deletion. Email developers [at] strava.com to get permission, if they're feeling generous.
	 */
	public static final boolean STRAVA_ALLOWS_COMMENTS_WRITE = Boolean.parseBoolean(RESOURCE_BUNDLE.getString("strava.permission.writeComment")); //$NON-NLS-1$

	/**
	 * Strava application-level permission to allow giving kudos. Email developers [at] strava.com to get permission, if they're feeling generous.
	 */
	public static final boolean STRAVA_ALLOWS_GIVE_KUDOS = Boolean.parseBoolean(RESOURCE_BUNDLE.getString("strava.permission.giveKudos")); //$NON-NLS-1$

	/**
	 * Strava application-level permission to allow access to the webhooks endpoint. Email developers [at] strava.com to get permission, if they're feeling generous.
	 */
	public static final boolean STRAVA_ALLOWS_WEBHOOKS_ENDPOINT = Boolean.parseBoolean(RESOURCE_BUNDLE.getString("strava.permission.webhooksEndpoint")); //$NON-NLS-1$

	/**
	 * Strava application-level permission to allow access to the challenges endpoint. Email developers [at] strava.com to get permission, if they're feeling generous.
	 */
	public static final boolean STRAVA_ALLOWS_CHALLENGES_ENDPOINT = Boolean.parseBoolean(RESOURCE_BUNDLE.getString("strava.permission.challengesEndpoint")); //$NON-NLS-1$

	/**
	 * Strava application-level permission to allow access to the beacon endpoint. Email developers [at] strava.com to get permission, if they're feeling generous.
	 */
	public static final boolean STRAVA_ALLOWS_BEACON_ENDPOINT = Boolean.parseBoolean(RESOURCE_BUNDLE.getString("strava.permission.beaconEndpoint")); //$NON-NLS-1$
}
