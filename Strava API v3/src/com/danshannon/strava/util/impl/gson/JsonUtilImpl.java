package com.danshannon.strava.util.impl.gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import com.danshannon.strava.api.auth.ref.AuthorisationApprovalPrompt;
import com.danshannon.strava.api.auth.ref.AuthorisationResponseType;
import com.danshannon.strava.api.auth.ref.AuthorisationScope;
import com.danshannon.strava.api.model.MapPoint;
import com.danshannon.strava.api.model.reference.ActivityType;
import com.danshannon.strava.api.model.reference.ActivityZoneType;
import com.danshannon.strava.api.model.reference.AgeGroup;
import com.danshannon.strava.api.model.reference.ClimbCategory;
import com.danshannon.strava.api.model.reference.ClubType;
import com.danshannon.strava.api.model.reference.FollowerState;
import com.danshannon.strava.api.model.reference.FrameType;
import com.danshannon.strava.api.model.reference.Gender;
import com.danshannon.strava.api.model.reference.LeaderboardDateRange;
import com.danshannon.strava.api.model.reference.MeasurementMethod;
import com.danshannon.strava.api.model.reference.PhotoType;
import com.danshannon.strava.api.model.reference.ResourceState;
import com.danshannon.strava.api.model.reference.SegmentActivityType;
import com.danshannon.strava.api.model.reference.SegmentExplorerActivityType;
import com.danshannon.strava.api.model.reference.SportType;
import com.danshannon.strava.api.model.reference.StreamResolutionType;
import com.danshannon.strava.api.model.reference.StreamSeriesDownsamplingType;
import com.danshannon.strava.api.model.reference.StreamType;
import com.danshannon.strava.api.model.reference.WeightClass;
import com.danshannon.strava.api.model.reference.WorkoutType;
import com.danshannon.strava.service.exception.ServiceException;
import com.danshannon.strava.util.JsonUtil;
import com.danshannon.strava.util.impl.gson.serializer.ActivityTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.ActivityZoneTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.AgeGroupSerializer;
import com.danshannon.strava.util.impl.gson.serializer.AuthorisationApprovalPromptSerializer;
import com.danshannon.strava.util.impl.gson.serializer.AuthorisationResponseTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.AuthorisationScopeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.ClimbCategorySerializer;
import com.danshannon.strava.util.impl.gson.serializer.ClubTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.FollowerStateSerializer;
import com.danshannon.strava.util.impl.gson.serializer.FrameTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.GenderSerializer;
import com.danshannon.strava.util.impl.gson.serializer.LeaderboardDateRangeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.MapPointSerializer;
import com.danshannon.strava.util.impl.gson.serializer.MeasurementMethodSerializer;
import com.danshannon.strava.util.impl.gson.serializer.PhotoTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.ResourceStateSerializer;
import com.danshannon.strava.util.impl.gson.serializer.SegmentActivityTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.SegmentExplorerActivityTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.SportTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.StreamResolutionTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.StreamSeriesDownsamplingTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.StreamTypeSerializer;
import com.danshannon.strava.util.impl.gson.serializer.WeightClassSerializer;
import com.danshannon.strava.util.impl.gson.serializer.WorkoutTypeSerializer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

/**
 * <p>
 * GSON implementation of JSON utilities
 * </p>
 * 
 * @author DShannon
 * 
 */
public class JsonUtilImpl implements JsonUtil {
	private GsonBuilder	gsonBuilder;
	private Gson		gson;

	public JsonUtilImpl() {
		gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		gsonBuilder.registerTypeAdapter(AuthorisationApprovalPrompt.class, new AuthorisationApprovalPromptSerializer());
		gsonBuilder.registerTypeAdapter(AuthorisationResponseType.class, new AuthorisationResponseTypeSerializer());
		gsonBuilder.registerTypeAdapter(AuthorisationScope.class, new AuthorisationScopeSerializer());
		gsonBuilder.registerTypeAdapter(ActivityType.class, new ActivityTypeSerializer());
		gsonBuilder.registerTypeAdapter(ActivityZoneType.class, new ActivityZoneTypeSerializer());
		gsonBuilder.registerTypeAdapter(AgeGroup.class, new AgeGroupSerializer());
		gsonBuilder.registerTypeAdapter(ClimbCategory.class, new ClimbCategorySerializer());
		gsonBuilder.registerTypeAdapter(ClubType.class, new ClubTypeSerializer());
		gsonBuilder.registerTypeAdapter(FollowerState.class, new FollowerStateSerializer());
		gsonBuilder.registerTypeAdapter(FrameType.class, new FrameTypeSerializer());
		gsonBuilder.registerTypeAdapter(Gender.class, new GenderSerializer());
		gsonBuilder.registerTypeAdapter(LeaderboardDateRange.class, new LeaderboardDateRangeSerializer());
		gsonBuilder.registerTypeAdapter(MapPoint.class, new MapPointSerializer());
		gsonBuilder.registerTypeAdapter(MeasurementMethod.class, new MeasurementMethodSerializer());
		gsonBuilder.registerTypeAdapter(PhotoType.class, new PhotoTypeSerializer());
		gsonBuilder.registerTypeAdapter(ResourceState.class, new ResourceStateSerializer());
		gsonBuilder.registerTypeAdapter(SegmentActivityType.class, new SegmentActivityTypeSerializer());
		gsonBuilder.registerTypeAdapter(SegmentExplorerActivityType.class, new SegmentExplorerActivityTypeSerializer());
		gsonBuilder.registerTypeAdapter(SportType.class, new SportTypeSerializer());
		gsonBuilder.registerTypeAdapter(StreamResolutionType.class, new StreamResolutionTypeSerializer());
		gsonBuilder.registerTypeAdapter(StreamSeriesDownsamplingType.class, new StreamSeriesDownsamplingTypeSerializer());
		gsonBuilder.registerTypeAdapter(StreamType.class, new StreamTypeSerializer());
		gsonBuilder.registerTypeAdapter(WeightClass.class, new WeightClassSerializer());
		gsonBuilder.registerTypeAdapter(WorkoutType.class, new WorkoutTypeSerializer());

		
		gson = gsonBuilder.create();
	}

	/**
	 * @see com.danshannon.strava.util.JsonUtil#deserialise(java.io.InputStream, java.lang.Class)
	 */
	@Override
	public <T> T deserialise(InputStream is, Class<T> class1) throws ServiceException {
		return gson.fromJson(new InputStreamReader(is), class1);
	}

	/**
	 * @see com.danshannon.strava.util.JsonUtil#deserialise(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T deserialise(String is, Class<T> class1) throws ServiceException {
		return gson.fromJson(is, class1);
	}

	/**
	 * @see com.danshannon.strava.util.JsonUtil#serialise(T)
	 */
	@Override
	public <T> String serialise(T object) throws ServiceException {
		return gson.toJson(object);
	}
	
	public Gson getGson() {
		return this.gson;
	}
}
