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
	private final GsonBuilder gsonBuilder;
	private final Gson gson;

	public JsonUtilImpl() {
		this.gsonBuilder = new GsonBuilder();
		this.gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		this.gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		this.gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
		this.gsonBuilder.registerTypeAdapter(AuthorisationApprovalPrompt.class, new AuthorisationApprovalPromptSerializer());
		this.gsonBuilder.registerTypeAdapter(AuthorisationResponseType.class, new AuthorisationResponseTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(AuthorisationScope.class, new AuthorisationScopeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaActivityType.class, new ActivityTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaActivityZoneType.class, new ActivityZoneTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaAgeGroup.class, new AgeGroupSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaClimbCategory.class, new ClimbCategorySerializer());
		this.gsonBuilder.registerTypeAdapter(StravaClubType.class, new ClubTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaFollowerState.class, new FollowerStateSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaFrameType.class, new FrameTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaGender.class, new GenderSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaLeaderboardDateRange.class, new LeaderboardDateRangeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaMapPoint.class, new MapPointSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaMeasurementMethod.class, new MeasurementMethodSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaPhotoType.class, new PhotoTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaResourceState.class, new ResourceStateSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaSegmentActivityType.class, new SegmentActivityTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaSegmentExplorerActivityType.class, new SegmentExplorerActivityTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaSportType.class, new SportTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaStreamResolutionType.class, new StreamResolutionTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaStreamSeriesDownsamplingType.class, new StreamSeriesDownsamplingTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaStream.class, new StravaStreamSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaStreamType.class, new StreamTypeSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaWeightClass.class, new WeightClassSerializer());
		this.gsonBuilder.registerTypeAdapter(StravaWorkoutType.class, new WorkoutTypeSerializer());

		this.gson = this.gsonBuilder.create();
	}

	/**
	 * @see stravajava.util.JsonUtil#deserialise(java.io.InputStream, java.lang.Class)
	 */
	@Override
	public <T> T deserialise(final InputStream is, final Class<T> class1) throws JsonSerialisationException {
		if (is == null) {
			return null;
		}
		return this.gson.fromJson(new InputStreamReader(is), class1);
	}

	/**
	 * @see stravajava.util.JsonUtil#deserialise(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T deserialise(final String is, final Class<T> class1) throws JsonSerialisationException {
		try {
			return this.gson.fromJson(is, class1);
		} catch (JsonParseException e) {
			throw new JsonSerialisationException("Failed to deserialise string '" + is + "' to " + class1.getName(), e);
		}
	}

	/**
	 * @see stravajava.util.JsonUtil#serialise(T)
	 */
	@Override
	public <T> String serialise(final T object) throws JsonSerialisationException {
		return this.gson.toJson(object);
	}

	public Gson getGson() {
		return this.gson;
	}
}
