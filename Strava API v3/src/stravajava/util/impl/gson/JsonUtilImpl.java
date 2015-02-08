package stravajava.util.impl.gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import stravajava.api.v3.auth.ref.AuthorisationApprovalPrompt;
import stravajava.api.v3.auth.ref.AuthorisationResponseType;
import stravajava.api.v3.auth.ref.AuthorisationScope;
import stravajava.api.v3.model.StravaMapPoint;
import stravajava.api.v3.model.StravaStream;
import stravajava.api.v3.model.reference.StravaActivityType;
import stravajava.api.v3.model.reference.StravaActivityZoneType;
import stravajava.api.v3.model.reference.StravaAgeGroup;
import stravajava.api.v3.model.reference.StravaClimbCategory;
import stravajava.api.v3.model.reference.StravaClubType;
import stravajava.api.v3.model.reference.StravaFollowerState;
import stravajava.api.v3.model.reference.StravaFrameType;
import stravajava.api.v3.model.reference.StravaGender;
import stravajava.api.v3.model.reference.StravaLeaderboardDateRange;
import stravajava.api.v3.model.reference.StravaMeasurementMethod;
import stravajava.api.v3.model.reference.StravaPhotoType;
import stravajava.api.v3.model.reference.StravaResourceState;
import stravajava.api.v3.model.reference.StravaSegmentActivityType;
import stravajava.api.v3.model.reference.StravaSegmentExplorerActivityType;
import stravajava.api.v3.model.reference.StravaSportType;
import stravajava.api.v3.model.reference.StravaStreamResolutionType;
import stravajava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import stravajava.api.v3.model.reference.StravaStreamType;
import stravajava.api.v3.model.reference.StravaWeightClass;
import stravajava.api.v3.model.reference.StravaWorkoutType;
import stravajava.util.JsonUtil;
import stravajava.util.exception.JsonSerialisationException;
import stravajava.util.impl.gson.serializer.ActivityTypeSerializer;
import stravajava.util.impl.gson.serializer.ActivityZoneTypeSerializer;
import stravajava.util.impl.gson.serializer.AgeGroupSerializer;
import stravajava.util.impl.gson.serializer.AuthorisationApprovalPromptSerializer;
import stravajava.util.impl.gson.serializer.AuthorisationResponseTypeSerializer;
import stravajava.util.impl.gson.serializer.AuthorisationScopeSerializer;
import stravajava.util.impl.gson.serializer.ClimbCategorySerializer;
import stravajava.util.impl.gson.serializer.ClubTypeSerializer;
import stravajava.util.impl.gson.serializer.FollowerStateSerializer;
import stravajava.util.impl.gson.serializer.FrameTypeSerializer;
import stravajava.util.impl.gson.serializer.GenderSerializer;
import stravajava.util.impl.gson.serializer.LeaderboardDateRangeSerializer;
import stravajava.util.impl.gson.serializer.MapPointSerializer;
import stravajava.util.impl.gson.serializer.MeasurementMethodSerializer;
import stravajava.util.impl.gson.serializer.PhotoTypeSerializer;
import stravajava.util.impl.gson.serializer.ResourceStateSerializer;
import stravajava.util.impl.gson.serializer.SegmentActivityTypeSerializer;
import stravajava.util.impl.gson.serializer.SegmentExplorerActivityTypeSerializer;
import stravajava.util.impl.gson.serializer.SportTypeSerializer;
import stravajava.util.impl.gson.serializer.StravaStreamSerializer;
import stravajava.util.impl.gson.serializer.StreamResolutionTypeSerializer;
import stravajava.util.impl.gson.serializer.StreamSeriesDownsamplingTypeSerializer;
import stravajava.util.impl.gson.serializer.StreamTypeSerializer;
import stravajava.util.impl.gson.serializer.WeightClassSerializer;
import stravajava.util.impl.gson.serializer.WorkoutTypeSerializer;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
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
		gsonBuilder.registerTypeAdapter(StravaActivityType.class, new ActivityTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaActivityZoneType.class, new ActivityZoneTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaAgeGroup.class, new AgeGroupSerializer());
		gsonBuilder.registerTypeAdapter(StravaClimbCategory.class, new ClimbCategorySerializer());
		gsonBuilder.registerTypeAdapter(StravaClubType.class, new ClubTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaFollowerState.class, new FollowerStateSerializer());
		gsonBuilder.registerTypeAdapter(StravaFrameType.class, new FrameTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaGender.class, new GenderSerializer());
		gsonBuilder.registerTypeAdapter(StravaLeaderboardDateRange.class, new LeaderboardDateRangeSerializer());
		gsonBuilder.registerTypeAdapter(StravaMapPoint.class, new MapPointSerializer());
		gsonBuilder.registerTypeAdapter(StravaMeasurementMethod.class, new MeasurementMethodSerializer());
		gsonBuilder.registerTypeAdapter(StravaPhotoType.class, new PhotoTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaResourceState.class, new ResourceStateSerializer());
		gsonBuilder.registerTypeAdapter(StravaSegmentActivityType.class, new SegmentActivityTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaSegmentExplorerActivityType.class, new SegmentExplorerActivityTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaSportType.class, new SportTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaStreamResolutionType.class, new StreamResolutionTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaStreamSeriesDownsamplingType.class, new StreamSeriesDownsamplingTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaStream.class, new StravaStreamSerializer());
		gsonBuilder.registerTypeAdapter(StravaStreamType.class, new StreamTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaWeightClass.class, new WeightClassSerializer());
		gsonBuilder.registerTypeAdapter(StravaWorkoutType.class, new WorkoutTypeSerializer());

		
		gson = gsonBuilder.create();
	}

	/**
	 * @see stravajava.util.JsonUtil#deserialise(java.io.InputStream, java.lang.Class)
	 */
	@Override
	public <T> T deserialise(InputStream is, Class<T> class1) throws JsonSerialisationException {
		if (is == null) {
			return null;
		}
		return gson.fromJson(new InputStreamReader(is), class1);
	}

	/**
	 * @see stravajava.util.JsonUtil#deserialise(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T deserialise(String is, Class<T> class1) throws JsonSerialisationException {
		try {
			return gson.fromJson(is, class1);
		} catch (JsonParseException e) {
			throw new JsonSerialisationException("Failed to deserialise string '" + is + "' to " + class1.getName(), e);
		}
	}

	/**
	 * @see stravajava.util.JsonUtil#serialise(T)
	 */
	@Override
	public <T> String serialise(T object) throws JsonSerialisationException {
		return gson.toJson(object);
	}
	
	public Gson getGson() {
		return this.gson;
	}
}
