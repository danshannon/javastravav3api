/**
 *
 */
package javastrava.api.v3.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.webhook.StravaEventSubscription;
import javastrava.api.v3.service.WebhookService;

/**
 * @author danshannon
 *
 */
public class WebhookServiceImpl extends StravaServiceImpl implements WebhookService {
	/**
	 * Name of the configuration file
	 */
	private static final String BUNDLE_NAME = "application"; //$NON-NLS-1$

	/**
	 * Resource bundle containing configuration properties
	 */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	/**
	 * Client id is obtained from Strava during registration
	 *
	 * @see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>
	 */
	private static final Integer CLIENT_ID = integer("strava.client_id"); //$NON-NLS-1$

	/**
	 * Client secret is obtained from Strava during registration
	 *
	 * @see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>
	 */
	private static final String CLIENT_SECRET = string("strava.client_secret"); //$NON-NLS-1$

	/**
	 * @param token The access token to associate with the service
	 * @return An instance of the service
	 */
	public static WebhookService instance(final Token token) {
		WebhookService service = token.getService(WebhookService.class);

		if (service == null) {
			service = new WebhookServiceImpl(token);
			token.addService(WebhookService.class, service);
		}

		return service;
	}

	/**
	 * @param key
	 *            The name of the property to return
	 * @return Integer value of the property from the resource bundle
	 */
	private static Integer integer(final String key) {
		return Integer.valueOf(RESOURCE_BUNDLE.getString(key));
	}

	/**
	 * Get the value of a String property
	 * @param property The property name
	 * @return The value of the property
	 */
	private static String string(final String property) {
		return RESOURCE_BUNDLE.getString(property);
	}

	/**
	 * @param token The access token to use in calls to the API
	 */
	protected WebhookServiceImpl(final Token token) {
		super(token);
	}

	/**
	 * @see javastrava.api.v3.service.StravaService#clearCache()
	 */
	@Override
	public void clearCache() {
		// There is no cache

	}

	/**
	 * @see javastrava.api.v3.service.WebhookService#createSubscription(javastrava.api.v3.model.webhook.StravaEventSubscription, String)
	 */
	@Override
	public StravaEventSubscription createSubscription(final StravaEventSubscription subscription, final String verifyToken) {
		return this.api.createSubscription(CLIENT_ID, CLIENT_SECRET, subscription.getObjectType(), subscription.getAspectType(), subscription.getCallbackURL(), verifyToken);
	}

	/**
	 * @see javastrava.api.v3.service.WebhookService#createSubscriptionAsync(javastrava.api.v3.model.webhook.StravaEventSubscription, String)
	 */
	@Override
	public CompletableFuture<StravaEventSubscription> createSubscriptionAsync(final StravaEventSubscription subscription, final String verifyToken) {
		return StravaServiceImpl.future(() -> {
			return this.api.createSubscription(CLIENT_ID, CLIENT_SECRET, subscription.getObjectType(), subscription.getAspectType(), subscription.getCallbackURL(), verifyToken);
		});
	}

	/**
	 * @see javastrava.api.v3.service.WebhookService#deleteSubscription(java.lang.Integer)
	 */
	@Override
	public void deleteSubscription(final Integer subscriptionId) {
		this.api.deleteSubscription(subscriptionId, CLIENT_ID, CLIENT_SECRET);
		return;

	}

	/**
	 * @see javastrava.api.v3.service.WebhookService#deleteSubscriptionAsync(java.lang.Integer)
	 */
	@Override
	public CompletableFuture<Void> deleteSubscriptionAsync(final Integer subscriptionId) {
		return StravaServiceImpl.future(() -> {
			this.api.deleteSubscription(subscriptionId, CLIENT_ID, CLIENT_SECRET);
			return null;
		});
	}

	/**
	 * @see javastrava.api.v3.service.WebhookService#listSubscriptions()
	 */
	@Override
	public List<StravaEventSubscription> listSubscriptions() {
		return Arrays.asList(this.api.listSubscriptions(CLIENT_ID, CLIENT_SECRET));
	}

	/**
	 * @see javastrava.api.v3.service.WebhookService#listSubscriptionsAsync()
	 */
	@Override
	public CompletableFuture<List<StravaEventSubscription>> listSubscriptionsAsync() {
		return StravaServiceImpl.future(() -> {
			return Arrays.asList(this.api.listSubscriptions(CLIENT_ID, CLIENT_SECRET));
		});
	}

}
