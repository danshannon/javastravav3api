package javastrava.api.v3.model;

import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.service.ActivityServices;
import javastrava.api.v3.service.impl.retrofit.ActivityServicesRetrofit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * Collection of fields that can be updated on an activity
 * </p>
 * @see ActivityServices#updateActivity(Integer, StravaActivityUpdate)
 * @see ActivityServicesRetrofit#updateActivity(Integer, StravaActivityUpdate)
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaActivityUpdate {
	/**
	 * <p>
	 * Constructor to create a {@link StravaActivityUpdate} from an existing {@link StravaActivity}
	 * @param activity The activity to be used to create the {@link StravaActivityUpdate} from
	 */
	public StravaActivityUpdate(final StravaActivity activity) {
		this.name = activity.getName();
		this.type = activity.getType();
		this.privateActivity = activity.getPrivateActivity();
		this.commute = activity.getCommute();
		this.trainer = activity.getTrainer();
		this.gearId = activity.getGearId();
		this.description = activity.getDescription();
	}
	
	private String name;
	private StravaActivityType type;
	@SerializedName("private")
	private Boolean privateActivity;
	private Boolean commute;
	private Boolean trainer;
	private String gearId;
	private String description;
}
