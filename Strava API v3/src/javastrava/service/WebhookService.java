package javastrava.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javastrava.model.webhook.StravaEventSubscription;

/**
 * <p>
 * API implementation of the Strava webhook events API
 * </p>
 *
 * <p>
 * Webhook push subscriptions allow an application to subscribe to events that occur within Strava’s infrastructure. These events are pushed to a URL designated by the subscription. Webhooks allow an
 * application to update athletes in real-time, and implement smarter data fetching.
 * </p>
 *
 * <p>
 * Requests to the subscription endpoint are made using the client_id and client_secret of the application because requests are made on behalf of an application.
 * </p>
 *
 * <p>
 * This API is only available to select applications. To request access please contact developers@strava.com
 * </p>
 *
 * @see <a href="http://strava.github.io/api/partner/v3/events/">http://strava.github.io/api/partner/v3/events/</a>
 *
 * @author Dan Shannon
 */
public interface WebhookService extends StravaService {
	/**
	 * <p>
	 * Creates a subscription to an allowed event
	 * </p>
	 *
	 * <p>
	 * The application must have permission to make use of the webhook API. Access can be requested by contacting developers -at- strava.com.
	 * </p>
	 *
	 * <p>
	 * The above request will send a GET request to callback url to verify existence
	 * </p>
	 *
	 * <p>
	 * Your response to this GET request must contain the hub.challenge token, ie. 15f7d1a91c1f40f8a748fd134752feb3 and have a response code of 200.
	 * </p>
	 *
	 * <p>
	 * On callback verification we respond to the original POST with the created subscription. If there is an error, a response containing the reason for failure will be returned.
	 * </p>
	 *
	 * <p>
	 * When an event occurs that corresponds to a push subscription, a POST request will be made to the callback url defined in the subscription. The payload will contain the object and aspect types
	 * affected, as well as information about the object and its owner if applicable.
	 * </p>
	 *
	 * <p>
	 * You should acknowledge the POST within a 2 second timeout–if you need to do more processing of the received information, you can do so in an asynchronous task.
	 * </p>
	 *
	 * <p>
	 * Additional metadata about the object is not included, and an application must decide how or if it wants to fetch updated data. For example, you may decide only to fetch new data for specific
	 * users, or after a certain number of activities have been uploaded.
	 * </p>
	 *
	 * @param clientId
	 *            The application's client id (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @param clientSecret
	 *            The application's client secret (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @param subscription
	 *            The subscription to create on Strava
	 * @param verifyToken
	 *            The verification token Strava should use when validating your endpoint
	 * @return Details as stored on Strava
	 */
	public StravaEventSubscription createSubscription(Integer clientId, String clientSecret, StravaEventSubscription subscription, String verifyToken);

	/**
	 * <p>
	 * Creates a subscription to an allowed event
	 * </p>
	 *
	 * <p>
	 * The application must have permission to make use of the webhook API. Access can be requested by contacting developers -at- strava.com.
	 * </p>
	 *
	 * <p>
	 * The above request will send a GET request to callback url to verify existence
	 * </p>
	 *
	 * <p>
	 * Your response to this GET request must contain the hub.challenge token, ie. 15f7d1a91c1f40f8a748fd134752feb3 and have a response code of 200.
	 * </p>
	 *
	 * <p>
	 * On callback verification Strava responds to the original POST with the created subscription. If there is an error, a response containing the reason for failure will be returned.
	 * </p>
	 *
	 * <p>
	 * When an event occurs that corresponds to a push subscription, a POST request will be made to the callback url defined in the subscription. The payload will contain the object and aspect types
	 * affected, as well as information about the object and its owner if applicable.
	 * </p>
	 *
	 * <p>
	 * You should acknowledge the POST within a 2 second timeout–if you need to do more processing of the received information, you can do so in an asynchronous task.
	 * </p>
	 *
	 * <p>
	 * Additional metadata about the object is not included, and an application must decide how or if it wants to fetch updated data. For example, you may decide only to fetch new data for specific
	 * users, or after a certain number of activities have been uploaded.
	 * </p>
	 *
	 * @param clientId
	 *            The application's client id (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @param clientSecret
	 *            The application's client secret (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @param subscription
	 *            The subscription to create on Strava
	 * @param verifyToken
	 *            The verification token Strava should use when validating your endpoint
	 * @return Details as stored on Strava
	 */
	public CompletableFuture<StravaEventSubscription> createSubscriptionAsync(Integer clientId, String clientSecret, StravaEventSubscription subscription, String verifyToken);

	/**
	 * <p>
	 * This request is used to unsubscribe from events.
	 * </p>
	 *
	 * <p>
	 * If the delete is successful, a 204 will be returned. Otherwise, an error will be returned containing the reason for a failure.
	 * </p>
	 *
	 * @param clientId
	 *            The application's client id (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @param clientSecret
	 *            The application's client secret (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @param id
	 *            Unique identifier of the subscription to be deleted
	 */
	public void deleteSubscription(Integer clientId, String clientSecret, Integer id);

	/**
	 * <p>
	 * This request is used to unsubscribe from events.
	 * </p>
	 *
	 * <p>
	 * If the delete is successful, a 204 will be returned. Otherwise, an error will be returned containing the reason for a failure.
	 * </p>
	 *
	 * @param clientId
	 *            The application's client id (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @param clientSecret
	 *            The application's client secret (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @param id
	 *            Unique identifier of the subscription to be deleted
	 * @return Future to call get() on when ready
	 */
	public CompletableFuture<Void> deleteSubscriptionAsync(Integer clientId, String clientSecret, Integer id);

	/**
	 * <p>
	 * This request is used to retrieve the summary representations of the subscriptions in place for the current application.
	 * </p>
	 *
	 * @param clientId
	 *            The application's client id (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @param clientSecret
	 *            The application's client secret (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @return List of current subscriptions for this application
	 */
	public List<StravaEventSubscription> listSubscriptions(Integer clientId, String clientSecret);

	/**
	 * <p>
	 * This request is used to retrieve the summary representations of the subscriptions in place for the current application.
	 * </p>
	 *
	 * @param clientId
	 *            The application's client id (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @param clientSecret
	 *            The application's client secret (see <a href="https://www.strava.com/settings/api">https://www.strava.com/settings/api</a>)
	 * @return List of current subscriptions for this application
	 */
	public CompletableFuture<List<StravaEventSubscription>> listSubscriptionsAsync(Integer clientId, String clientSecret);
}
