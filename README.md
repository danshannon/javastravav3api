javastravav3api
===============

Strava API v3 implementation written in Java v8

Javastrava is a functionally complete implementation of the Strava API (v3). It includes all the changes made to the API up to April 7, 2015.

It consists of 2 layers which implement the API:

1. A raw implementation of the API using Retrofit - with this it's up to your code to deal with exceptions, and Strava's foibles
2. An abstracted implementation of the API which simplifies and abstracts the API, as well as adding several useful features (automatic handling of 404 and 401 errors; dealing with rate limiting; dealing with privacy; and handling a bunch of workarounds where the behaviour of the API is inconsistent) and removing some restrictions (particularly on paging)

Maven
=====
javastrava is available on Maven. Just add this to your POM:

```
<dependency>
	<groupId>com.github.danshannon</groupId>
	<artifactId>javastrava-api</artifactId>
	<version>0.9.1-BETA</version>
</dependency>
```

Use (full implementation)
=========================
To use Javastrava, all you really need is an access token. Javastrava doesn't provide a mechanism for acquiring one via OAuth - it's a vanilla, headless implementation of the API. You will need to register with Strava for an API key, and you will get an API key (access token) automatically when you register your app. There is a hack which gets tokens through the OAuth process on the Strava website built into the test suite, see test.utils.TestUtils.getValidToken(), but remember that it's a hack!

Basically, once you've gone through the OAuth validation process, Strava returns a one-off code. You then need to exchange that code for a token:

```
AuthorisationService service = new AuthorisationServiceImpl();
Token token = service.tokenExchange({application_client_id}, {client_secret}, code);
```

Once you've got an access token, life is pretty simple really. Getting a service implementation looks like this:

`Strava strava = new Strava(token)`

Then, getting an athlete looks like this:

`StravaAthlete athlete = strava.getAthlete(id);`

Use (raw API)
=============
If you prefer to use the raw API, then a similar approach is required. Again, it's your problem to get through the OAuth process until you've got a code. Then, to get a token:

```
AuthorisationAPI auth = API.authorisationInstance();
TokenResponse response = auth.tokenExchange({application_client_id}, {client_secret}, code);
Token token = new Token(response);
```

Now we can get an API instance:

`API api = new API(token);`

And finally, the athlete:

`StravaAthlete athlete = api.getAthlete(id);`

Token Management
================
The `TokenManager` class provides a cache for all active tokens, for all users who have given permission to your application. Token exchange (above) will add each token to the token manager via `TokenManager.instance().storeToken(token)`.

You can then retrieve a token from the TokenManager later on via `TokenManager.instance().retrieveToken(username)`. The username is the email address that the user logs in to Strava with; you can find it with `token.getAthlete().getEmail()`

The API doesn't currently cater for persistence of tokens or of the token manager; that's up to your application to do.

Tricks of the trade
===================
The Strava API can be a bit, well, weird when you use it in anger. The interaction between privacy settings, authentication and so on isn't always consistent in the API. What we've done is this:

- If the object you're after doesn't exist, service methods will return `null`. API methods will throw a `NotFoundException`
- If the object you're after is private, service methods will return an empty object - either a list with no entries, or a model object with only the id populated. We don't worry about this too much from a privacy point of view, because all you get is that an object exists, but you don't get any of the data. Athletes are different - they are returned with a limited view of the athlete, rather than an empty athlete. API methods throw an `UnauthorisedException`
- If your token has been revoked, or wasn't valid in the first place, you'll see an `InvalidTokenException` (which is unchecked) get thrown
- If your token doesn't have the authorisation scope needed for the operation that you're attempting, you'll see an `UnauthorisedException` (which is unchecked) get thrown.
- If you encounter network errors accessing the Strava API, a `StravaAPINetworkException` is thrown.
- If you exceed your rate limit, then you'll see a `StravaAPIRateLimitException`.
- If Strava returns a `503 Service Temporarily Unavailable` status, then you'll see a `StravaServiceUnavailableException`.
- If Strava returns a `500 Internal Server Error`, then you'll get a `StravaInternalServerErrorException`.
- Other unexpected errors will cause a `StravaUnknownAPIException` to be thrown.

Paging
======
We've provided a stack of alternate method signatures for all the API endpoints, both with and without the paging options. 

The methods that do not include paging instructions will return only the first page from the Strava API, *not* everything. There are methods that *do* return everything, they're typically called `listAll*`. Be careful using these...

The methods that do include paging instructions are built to override the Strava paging limits. If you really want, you can ask for 10,000 or more activities at once, not Strava's artificial limit of 200 per page. Be aware, though, that internally we're still bound by the Strava limits, so asking for 10,000 activities will result in 500 calls to the API! That's going to exhaust your throttling limits (by default 600 calls every 15 minutes) pretty fast...

To use the paging options, you pass in a stravajava.util.Paging object as the pagingInstruction parameter. Have a look; it's amazimgly flexible!

Leaderboards
============
The Strava API is annoying when it passes your own results along with every page of a leaderboard. We've hacked that out, so that in the stravajava.api.v3.model.StravaSegmentLeaderboard definitions you'll see there are 2 collections of entries - <code>entries</code> is the one that you actually asked for, <code>athleteEntries</code> is the one that relates to the 5 entries around the authenticated athlete / you. Should be *much* simpler to deal with!

Testing
=======
There's a test suite at https://github.com/danshannon/javastrava-test

Dependencies
============
- The REST client is written using Retrofit, because it makes life ridiculously easy
- JSON serialisation uses GSON
