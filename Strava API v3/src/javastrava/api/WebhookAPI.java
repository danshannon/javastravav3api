package javastrava.api;

import javastrava.api.async.StravaAPICallback;
import javastrava.model.StravaResponse;
import javastrava.model.webhook.StravaEventSubscription;
import javastrava.model.webhook.reference.StravaSubscriptionAspectType;
import javastrava.model.webhook.reference.StravaSubscriptionObjectType;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * API implementation of the Strava webhook events API
 * </p>
 *
 * <p>
 * Webhook push subscriptions allow an application to subscribe to events that occur within Strava’s infrastructure. These events are pushed to a URL designated by the subscription. Webhooks allow an application to update athletes in real-time, and implement smarter data fetching.
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
 *
 */
public interface WebhookAPI {
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
	 * When an event occurs that corresponds to a push subscription, a POST request will be made to the callback url defined in the subscription. The payload will contain the object and aspect types affected, as well as information about the object and its owner if applicable.
	 * </p>
	 *
	 * <p>
	 * You should acknowledge the POST within a 2 second timeout–if you need to do more processing of the received information, you can do so in an asynchronous task.
	 * </p>
	 *
	 * <p>
	 * Additional metadata about the object is not included, and an application must decide how or if it wants to fetch updated data. For example, you may decide only to fetch new data for specific users, or after a certain number of activities have been uploaded.
	 * </p>
	 *
	 * @param clientId Application's id, as obtained during registration with Strava
	 * @param clientSecret Application's secret, as obtained during Strava registration
	 * @param objectType The type of object being subscribed to
	 * @param aspectType The aspect being subscribed to
	 * @param callbackURL (Max 255 characters) URL which Strava will callback with an HTTP GET to verify the existence of the webhook endpoint, then subsequently will POST to with subscribed events
	 * @param verifyToken The token's value will be included in the GET callback request when verifying the endpoint
	 * @return Details of the event subscription
	 */
	@POST("/subscriptions")
	public StravaEventSubscription createSubscription(@Query("client_id") Integer clientId, @Query("client_secret") String clientSecret, @Query("object_type_id") StravaSubscriptionObjectType objectType, @Query("aspect_type_id") StravaSubscriptionAspectType aspectType, @Query("callback_url") String callbackURL, @Query("verify_token") String verifyToken);

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
	 * When an event occurs that corresponds to a push subscription, a POST request will be made to the callback url defined in the subscription. The payload will contain the object and aspect types affected, as well as information about the object and its owner if applicable.
	 * </p>
	 *
	 * <p>
	 * You should acknowledge the POST within a 2 second timeout–if you need to do more processing of the received information, you can do so in an asynchronous task.
	 * </p>
	 *
	 * <p>
	 * Additional metadata about the object is not included, and an application must decide how or if it wants to fetch updated data. For example, you may decide only to fetch new data for specific users, or after a certain number of activities have been uploaded.
	 * </p>
	 *
	 * @param clientId Application's id, as obtained during registration with Strava
	 * @param clientSecret Application's secret, as obtained during Strava registration
	 * @param objectType The type of object being subscribed to
	 * @param aspectType The aspect being subscribed to
	 * @param callbackURL (Max 255 characters) URL which Strava will callback with an HTTP GET to verify the existence of the webhook endpoint, then subsequently will POST to with subscribed events
	 * @param verifyToken The token's value will be included in the GET callback request when verifying the endpoint
	 * @param callback Callback for execution when the asynchronous call completes
	 */
	@POST("/subscriptions")
	public void createSubscription(@Query("client_id") Integer clientId, @Query("client_secret") String clientSecret, @Query("object_type_id") StravaSubscriptionObjectType objectType, @Query("aspect_type_id") StravaSubscriptionAspectType aspectType, @Query("callback_url") String callbackURL, @Query("verify_token") String verifyToken, StravaAPICallback<StravaEventSubscription> callback);

	/**
	 * <p>
	 * This request is used to unsubscribe from events.
	 * </p>
	 *
	 * <p>
	 * If the delete is successful, a 204 will be returned. Otherwise, an error will be returned containing the reason for a failure.
	 * </p>
	 * @param subscriptionId The id of the subscription to be deleted
	 * @param clientId Application's id, as obtained during registration with Strava
	 * @param clientSecret Application's secret, as obtained during Strava registration
	 * @return Returns nothing on success
	 */
	@DELETE("/subscriptions/{id}")
	public StravaResponse deleteSubscription(@Path("id") Integer subscriptionId, @Query("client_id") Integer clientId, @Query("client_secret") String clientSecret);

	/**
	 * <p>
	 * This request is used to unsubscribe from events.
	 * </p>
	 *
	 * <p>
	 * If the delete is successful, a 204 will be returned. Otherwise, an error will be returned containing the reason for a failure.
	 * </p>
	 * @param subscriptionId The id of the subscription to be deleted
	 * @param clientId Application's id, as obtained during registration with Strava
	 * @param clientSecret Application's secret, as obtained during Strava registration
	 * @param callback Callback for execution when the asynchronous call completes
	 */
	@DELETE("/subscriptions/{id}")
	public void deleteSubscription(@Path("id") Integer subscriptionId, @Query("client_id") Integer clientId, @Query("client_secret") String clientSecret, StravaAPICallback<StravaResponse> callback);

	/**
	 * <p>
	 * This request is used to retrieve the summary representations of the subscriptions in place for the current application.
	 * </p>
	 *
	 * @param clientId Application's id, as obtained during registration with Strava
	 * @param clientSecret Application's secret, as obtained during Strava registration
	 * @return Returns an array of summary representations of the application's current subscriptions
	 */
	@GET("/subscriptions")
	public StravaEventSubscription[] listSubscriptions(@Query("client_id") Integer clientId, @Query("client_secret") String clientSecret);

	/**
	 * <p>
	 * This request is used to retrieve the summary representations of the subscriptions in place for the current application.
	 * </p>
	 *
	 * @param clientId Application's id, as obtained during registration with Strava
	 * @param clientSecret Application's secret, as obtained during Strava registration
	 * @param callback Callback for execution when the asynchronous call completes
	 */
	@GET("/subscriptions")
	public void listSubscriptions(@Query("client_id") Integer clientId, @Query("client_secret") String clientSecret, StravaAPICallback<StravaEventSubscription[]> callback);
}
