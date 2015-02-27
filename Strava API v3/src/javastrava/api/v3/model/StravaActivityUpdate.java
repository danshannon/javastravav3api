package javastrava.api.v3.model;

import javastrava.api.v3.model.reference.StravaActivityType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.google.gson.annotations.SerializedName;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaActivityUpdate {
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
