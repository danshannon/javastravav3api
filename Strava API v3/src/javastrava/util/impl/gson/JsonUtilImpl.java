package javastrava.util.impl.gson;

import java.io.InputStream;
import java.io.InputStreamReader;

import javastrava.api.v3.auth.ref.AuthorisationApprovalPrompt;
import javastrava.api.v3.auth.ref.AuthorisationResponseType;
import javastrava.api.v3.auth.ref.AuthorisationScope;
import javastrava.api.v3.model.StravaMapPoint;
import javastrava.api.v3.model.StravaStream;
import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.model.reference.StravaActivityZoneType;
import javastrava.api.v3.model.reference.StravaAgeGroup;
import javastrava.api.v3.model.reference.StravaClimbCategory;
import javastrava.api.v3.model.reference.StravaClubType;
import javastrava.api.v3.model.reference.StravaFollowerState;
import javastrava.api.v3.model.reference.StravaFrameType;
import javastrava.api.v3.model.reference.StravaGender;
import javastrava.api.v3.model.reference.StravaLeaderboardDateRange;
import javastrava.api.v3.model.reference.StravaMeasurementMethod;
import javastrava.api.v3.model.reference.StravaPhotoType;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.api.v3.model.reference.StravaSegmentActivityType;
import javastrava.api.v3.model.reference.StravaSegmentExplorerActivityType;
import javastrava.api.v3.model.reference.StravaSportType;
import javastrava.api.v3.model.reference.StravaStreamResolutionType;
import javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.api.v3.model.reference.StravaStreamType;
import javastrava.api.v3.model.reference.StravaWeightClass;
import javastrava.api.v3.model.reference.StravaWorkoutType;
import javastrava.config.Messages;
import javastrava.config.Strava;
import javastrava.util.JsonUtil;
import javastrava.util.exception.JsonSerialisationException;
import javastrava.util.impl.gson.serializer.ActivityTypeSerializer;
import javastrava.util.impl.gson.serializer.ActivityZoneTypeSerializer;
import javastrava.util.impl.gson.serializer.AgeGroupSerializer;
import javastrava.util.impl.gson.serializer.AuthorisationApprovalPromptSerializer;
import javastrava.util.impl.gson.serializer.AuthorisationResponseTypeSerializer;
import javastrava.util.impl.gson.serializer.AuthorisationScopeSerializer;
import javastrava.util.impl.gson.serializer.ClimbCategorySerializer;
import javastrava.util.impl.gson.serializer.ClubTypeSerializer;
import javastrava.util.impl.gson.serializer.FollowerStateSerializer;
import javastrava.util.impl.gson.serializer.FrameTypeSerializer;
import javastrava.util.impl.gson.serializer.GenderSerializer;
import javastrava.util.impl.gson.serializer.LeaderboardDateRangeSerializer;
import javastrava.util.impl.gson.serializer.MapPointSerializer;
import javastrava.util.impl.gson.serializer.MeasurementMethodSerializer;
import javastrava.util.impl.gson.serializer.PhotoTypeSerializer;
import javastrava.util.impl.gson.serializer.ResourceStateSerializer;
import javastrava.util.impl.gson.serializer.SegmentActivityTypeSerializer;
import javastrava.util.impl.gson.serializer.SegmentExplorerActivityTypeSerializer;
import javastrava.util.impl.gson.serializer.SportTypeSerializer;
import javastrava.util.impl.gson.serializer.StravaStreamSerializer;
import javastrava.util.impl.gson.serializer.StreamResolutionTypeSerializer;
import javastrava.util.impl.gson.serializer.StreamSeriesDownsamplingTypeSerializer;
import javastrava.util.impl.gson.serializer.StreamTypeSerializer;
import javastrava.util.impl.gson.serializer.WeightClassSerializer;
import javastrava.util.impl.gson.serializer.WorkoutTypeSerializer;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

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
		this.gsonBuilder.setDateFormat(Strava.DATE_FORMAT);
		//this.gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
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
	 * @see javastrava.util.JsonUtil#deserialise(java.io.InputStream, java.lang.Class)
	 */
	@Override
	public <T> T deserialise(final InputStream is, final Class<T> class1) throws JsonSerialisationException {
		if (is == null) {
			return null;
		}
		return this.gson.fromJson(new InputStreamReader(is), class1);
	}

	/**
	 * @see javastrava.util.JsonUtil#deserialise(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T deserialise(final String is, final Class<T> class1) throws JsonSerialisationException {
		try {
			return this.gson.fromJson(is, class1);
		} catch (JsonParseException e) {
			throw new JsonSerialisationException(String.format(Messages.getString("JsonUtilImpl.failedToDeserialiseString"), is, class1.getName()), e); //$NON-NLS-1$
		}
	}

	/**
	 * @see javastrava.util.JsonUtil#serialise(Object)
	 */
	@Override
	public <T> String serialise(final T object) throws JsonSerialisationException {
		return this.gson.toJson(object);
	}

	public Gson getGson() {
		return this.gson;
	}
}
