package javastrava.json.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import javastrava.auth.ref.AuthorisationApprovalPrompt;
import javastrava.auth.ref.AuthorisationResponseType;
import javastrava.auth.ref.AuthorisationScope;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.JsonUtil;
import javastrava.json.exception.JsonSerialisationException;
import javastrava.json.impl.serializer.ActivityTypeSerializer;
import javastrava.json.impl.serializer.ActivityZoneTypeSerializer;
import javastrava.json.impl.serializer.AgeGroupSerializer;
import javastrava.json.impl.serializer.AthleteTypeSerializer;
import javastrava.json.impl.serializer.AuthorisationApprovalPromptSerializer;
import javastrava.json.impl.serializer.AuthorisationResponseTypeSerializer;
import javastrava.json.impl.serializer.AuthorisationScopeSerializer;
import javastrava.json.impl.serializer.ChallengeTypeSerializer;
import javastrava.json.impl.serializer.ClimbCategorySerializer;
import javastrava.json.impl.serializer.ClubMembershipStatusSerializer;
import javastrava.json.impl.serializer.ClubTypeSerializer;
import javastrava.json.impl.serializer.FollowerStateSerializer;
import javastrava.json.impl.serializer.FrameTypeSerializer;
import javastrava.json.impl.serializer.GenderSerializer;
import javastrava.json.impl.serializer.LeaderboardDateRangeSerializer;
import javastrava.json.impl.serializer.LocalDateSerializer;
import javastrava.json.impl.serializer.LocalDateTimeSerializer;
import javastrava.json.impl.serializer.MapPointSerializer;
import javastrava.json.impl.serializer.MeasurementMethodSerializer;
import javastrava.json.impl.serializer.PhotoTypeSerializer;
import javastrava.json.impl.serializer.ResourceStateSerializer;
import javastrava.json.impl.serializer.RouteSubTypeSerializer;
import javastrava.json.impl.serializer.RouteTypeSerializer;
import javastrava.json.impl.serializer.RunningRaceTypeSerializer;
import javastrava.json.impl.serializer.SegmentActivityTypeSerializer;
import javastrava.json.impl.serializer.SegmentExplorerActivityTypeSerializer;
import javastrava.json.impl.serializer.SkillLevelSerializer;
import javastrava.json.impl.serializer.SportTypeSerializer;
import javastrava.json.impl.serializer.StravaPhotoSourceSerializer;
import javastrava.json.impl.serializer.StravaStreamSerializer;
import javastrava.json.impl.serializer.StreamResolutionTypeSerializer;
import javastrava.json.impl.serializer.StreamSeriesDownsamplingTypeSerializer;
import javastrava.json.impl.serializer.StreamTypeSerializer;
import javastrava.json.impl.serializer.SubscriptionAspectTypeSerializer;
import javastrava.json.impl.serializer.SubscriptionObjectTypeSerializer;
import javastrava.json.impl.serializer.TerrainTypeSerializer;
import javastrava.json.impl.serializer.WeekOfMonthSerializer;
import javastrava.json.impl.serializer.WeightClassSerializer;
import javastrava.json.impl.serializer.WorkoutTypeSerializer;
import javastrava.json.impl.serializer.ZonedDateTimeSerializer;
import javastrava.model.StravaMapPoint;
import javastrava.model.StravaStream;
import javastrava.model.reference.StravaActivityType;
import javastrava.model.reference.StravaActivityZoneType;
import javastrava.model.reference.StravaAgeGroup;
import javastrava.model.reference.StravaAthleteType;
import javastrava.model.reference.StravaChallengeType;
import javastrava.model.reference.StravaClimbCategory;
import javastrava.model.reference.StravaClubMembershipStatus;
import javastrava.model.reference.StravaClubType;
import javastrava.model.reference.StravaFollowerState;
import javastrava.model.reference.StravaFrameType;
import javastrava.model.reference.StravaGender;
import javastrava.model.reference.StravaLeaderboardDateRange;
import javastrava.model.reference.StravaMeasurementMethod;
import javastrava.model.reference.StravaPhotoSource;
import javastrava.model.reference.StravaPhotoType;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaRouteSubType;
import javastrava.model.reference.StravaRouteType;
import javastrava.model.reference.StravaRunningRaceType;
import javastrava.model.reference.StravaSegmentActivityType;
import javastrava.model.reference.StravaSegmentExplorerActivityType;
import javastrava.model.reference.StravaSkillLevel;
import javastrava.model.reference.StravaSportType;
import javastrava.model.reference.StravaStreamResolutionType;
import javastrava.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.model.reference.StravaStreamType;
import javastrava.model.reference.StravaTerrainType;
import javastrava.model.reference.StravaWeekOfMonth;
import javastrava.model.reference.StravaWeightClass;
import javastrava.model.reference.StravaWorkoutType;
import javastrava.model.webhook.reference.StravaSubscriptionAspectType;
import javastrava.model.webhook.reference.StravaSubscriptionObjectType;

/**
 * <p>
 * GSON implementation of JSON utilities
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class JsonUtilImpl implements JsonUtil {
	/**
	 * GSON instance used for all JSON deserialisation and serialisation
	 */
	private final Gson gson;

	/**
	 * Default constructor
	 */
	public JsonUtilImpl() {
		final GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		gsonBuilder.setDateFormat(StravaConfig.DATE_FORMAT);
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
		gsonBuilder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer());
		gsonBuilder.registerTypeAdapter(AuthorisationApprovalPrompt.class, new AuthorisationApprovalPromptSerializer());
		gsonBuilder.registerTypeAdapter(AuthorisationResponseType.class, new AuthorisationResponseTypeSerializer());
		gsonBuilder.registerTypeAdapter(AuthorisationScope.class, new AuthorisationScopeSerializer());
		gsonBuilder.registerTypeAdapter(StravaActivityType.class, new ActivityTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaActivityZoneType.class, new ActivityZoneTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaAgeGroup.class, new AgeGroupSerializer());
		gsonBuilder.registerTypeAdapter(StravaAthleteType.class, new AthleteTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaChallengeType.class, new ChallengeTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaClimbCategory.class, new ClimbCategorySerializer());
		gsonBuilder.registerTypeAdapter(StravaClubType.class, new ClubTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaClubMembershipStatus.class, new ClubMembershipStatusSerializer());
		gsonBuilder.registerTypeAdapter(StravaFollowerState.class, new FollowerStateSerializer());
		gsonBuilder.registerTypeAdapter(StravaFrameType.class, new FrameTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaGender.class, new GenderSerializer());
		gsonBuilder.registerTypeAdapter(StravaLeaderboardDateRange.class, new LeaderboardDateRangeSerializer());
		gsonBuilder.registerTypeAdapter(StravaMapPoint.class, new MapPointSerializer());
		gsonBuilder.registerTypeAdapter(StravaMeasurementMethod.class, new MeasurementMethodSerializer());
		gsonBuilder.registerTypeAdapter(StravaPhotoSource.class, new StravaPhotoSourceSerializer());
		gsonBuilder.registerTypeAdapter(StravaPhotoType.class, new PhotoTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaResourceState.class, new ResourceStateSerializer());
		gsonBuilder.registerTypeAdapter(StravaRouteType.class, new RouteTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaRouteSubType.class, new RouteSubTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaRunningRaceType.class, new RunningRaceTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaSegmentActivityType.class, new SegmentActivityTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaSegmentExplorerActivityType.class, new SegmentExplorerActivityTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaSkillLevel.class, new SkillLevelSerializer());
		gsonBuilder.registerTypeAdapter(StravaSportType.class, new SportTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaStreamResolutionType.class, new StreamResolutionTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaStreamSeriesDownsamplingType.class, new StreamSeriesDownsamplingTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaStream.class, new StravaStreamSerializer());
		gsonBuilder.registerTypeAdapter(StravaStreamType.class, new StreamTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaSubscriptionAspectType.class, new SubscriptionAspectTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaSubscriptionObjectType.class, new SubscriptionObjectTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaTerrainType.class, new TerrainTypeSerializer());
		gsonBuilder.registerTypeAdapter(StravaWeekOfMonth.class, new WeekOfMonthSerializer());
		gsonBuilder.registerTypeAdapter(StravaWeightClass.class, new WeightClassSerializer());
		gsonBuilder.registerTypeAdapter(StravaWorkoutType.class, new WorkoutTypeSerializer());

		this.gson = gsonBuilder.create();
	}

	/**
	 * @see javastrava.json.JsonUtil#deserialise(java.io.InputStream, java.lang.Class)
	 */
	@Override
	public <T> T deserialise(final InputStream is, final Class<T> class1) throws JsonSerialisationException {
		if (is == null) {
			return null;
		}
		return this.gson.fromJson(new InputStreamReader(is), class1);
	}

	/**
	 * @see javastrava.json.JsonUtil#deserialise(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T deserialise(final String is, final Class<T> class1) throws JsonSerialisationException {
		try {
			return this.gson.fromJson(is, class1);
		} catch (final JsonParseException e) {
			throw new JsonSerialisationException(String.format(Messages.string("JsonUtilImpl.failedToDeserialiseString"), is, class1.getName()), e); //$NON-NLS-1$
		}
	}

	/**
	 * @return The GSON
	 */
	public Gson getGson() {
		return this.gson;
	}

	/**
	 * @see javastrava.json.JsonUtil#serialise(Object)
	 */
	@Override
	public <T> String serialise(final T object) throws JsonSerialisationException {
		return this.gson.toJson(object);
	}
}
