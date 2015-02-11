javastravav3api
===============

Strava API v3 implementation written in Java v7

JavaStrava is a functionally complete implementation of the Strava API (v3).

Dependencies
============
We use Lombok for simplicity; if you want to modify or compile the project, you'll need the appropriate plugin for the Java compiler from lombok.org

Use
===
To use StravaJava, all you really need is an access token. StravaJava doesn't provide a mechanism for acquiring one via OAuth - it's a vanilla, headless implementation of the API. You will need to register with Strava for an API key, and you will get an API key (access token) automatically when you register your app.

Once you've got an access token, life is pretty simple really. Getting a service implementation (in this case for the athlete service endpoints) looks like this:

AthleteServices service = AthleteServicesImpl.implementation(accessToken);

Then, getting an athlete looks like this:

StravaAthlete athlete = service.getAthlete(id);

Tricks of the trade
===================
The Strava API can be a bit, well, weird when you use it in anger. The interaction between privacy settings, authentication and so on isn't always consistent in the API. What we've done is this:

- If the object you're after doesn't exist, service methods will return <code>null</code>
- If the object you're after is private, service methods will return an empty object - either a list with no entries, or a model object with only the id populated. We don't worry about this too much from a privacy point of view, because all you get is that an object exists, but you don't get any of the data. Athletes are different - they are returned with a limited view of the athlete, rather than an empty athlete

Paging
======
We've provided a stack of alternate method signatures for all the API endpoints, both with and without the paging options. 

The methods that do not include paging instructions will return only the first page from the Strava API, *not* everything.

The methods that do include paging instructions are built to override the Strava paging limits. If you really want, you can ask for 10,000 or more activities at once, not Strava's artificial limit of 200 per page. Be aware, though, that internally we're still bound by the Strava limits, so asking for 10,000 activities will result in 500 calls to the API! That's going to exhaust your throttling limits (by default 600 calls every 15 minutes) pretty fast...


