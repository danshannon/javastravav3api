javastravav3api
===============

Strava API v3 implementation written in Java v7

JavaStrava is a functionally complete implementation of the Strava API (v3). It includes all the changes made to the API up to Feb 7, 2015.

Maven
=====
javastrava is available on Maven. Just add this to your POM.

```
		<dependency>
			<groupId>com.github.danshannon</groupId>
			<artifactId>javastrava-api</artifactId>
			<version>0.1.0-ALPHA</version>
		</dependency>
```

Dependencies
============
- We use Lombok for simplicity of bean code; if you want to modify or compile the project, you'll need the appropriate plugin for your chosen IDE from lombok.org
- The REST client is written using Retrofit, because it makes life ridiculously easy

Use
===
To use StravaJava, all you really need is an access token. StravaJava doesn't provide a mechanism for acquiring one via OAuth - it's a vanilla, headless implementation of the API. You will need to register with Strava for an API key, and you will get an API key (access token) automatically when you register your app. There is a hack which gets tokens through the OAuth process on the Strava website built into the test suite, see test.utils.TestHttpUtils, but remember that it's a hack!

Once you've got an access token, life is pretty simple really. Getting a service implementation (in this case for the athlete service endpoints) looks like this:

<code>AthleteServices service = AthleteServicesImpl.implementation(accessToken);</code>

Then, getting an athlete looks like this:

<code>StravaAthlete athlete = service.getAthlete(id);</code>

Tricks of the trade
===================
The Strava API can be a bit, well, weird when you use it in anger. The interaction between privacy settings, authentication and so on isn't always consistent in the API. What we've done is this:

- If the object you're after doesn't exist, service methods will return <code>null</code>
- If the object you're after is private, service methods will return an empty object - either a list with no entries, or a model object with only the id populated. We don't worry about this too much from a privacy point of view, because all you get is that an object exists, but you don't get any of the data. Athletes are different - they are returned with a limited view of the athlete, rather than an empty athlete
- If your token has been revoked, or wasn't valid in the first place, or doesn't have the authorisation scope needed for the operation that you're attempting, you'll see an UnauthorisedException (which is unchecked) get thrown.

Paging
======
We've provided a stack of alternate method signatures for all the API endpoints, both with and without the paging options. 

The methods that do not include paging instructions will return only the first page from the Strava API, *not* everything.

The methods that do include paging instructions are built to override the Strava paging limits. If you really want, you can ask for 10,000 or more activities at once, not Strava's artificial limit of 200 per page. Be aware, though, that internally we're still bound by the Strava limits, so asking for 10,000 activities will result in 500 calls to the API! That's going to exhaust your throttling limits (by default 600 calls every 15 minutes) pretty fast...

To use the paging options, you pass in a stravajava.util.Paging object as the pagingInstruction parameter. Have a look; it's amazimgly flexible!

Testing
=======
The test suite which comes with JavaStrava (everything inside the test package) is attempting to be a complete exercise of the whole API and all the options that come with it. You're encouraged to use it. To configure the tests, rename the sample-test-config.properties file to test-config.properties and set all the relevant values (they're documented). You might need to set up some additional accounts on Strava (and email accounts on, say, gmail) to help with that.

Leaderboards
============
The Strava API is annoying when it passes your own results along with every page of a leaderboard. We've hacked that out, so that in the stravajava.api.v3.model.StravaSegmentLeaderboard definitions you'll see there are 2 collections of entries - <code>entries</code> is the one that you actually asked for, <code>athleteEntries</code> is the one that relates to the 5 entries around the authenticated athlete / you. Should be *much* simpler to deal with!
