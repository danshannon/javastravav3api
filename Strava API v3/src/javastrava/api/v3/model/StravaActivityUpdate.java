package javastrava.api.v3.model;

import com.google.gson.annotations.SerializedName;

import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.rest.ActivityAPI;
import javastrava.api.v3.service.ActivityService;

/**
 * <p>
 * Collection of fields that can be updated on an activity
 * </p>
 * @see ActivityService#updateActivity(Integer, StravaActivityUpdate)
 * @see ActivityAPI#updateActivity(Integer, StravaActivityUpdate)
 * @author Dan Shannon
 *
 */
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
	
	private final String name;
	private final StravaActivityType type;
	@SerializedName("private")
	private final Boolean privateActivity;
	private final Boolean commute;
	private final Boolean trainer;
	private final String gearId;
	private final String description;
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return the type
	 */
	public StravaActivityType getType() {
		return this.type;
	}
	/**
	 * @return the privateActivity
	 */
	public Boolean getPrivateActivity() {
		return this.privateActivity;
	}
	/**
	 * @return the commute
	 */
	public Boolean getCommute() {
		return this.commute;
	}
	/**
	 * @return the trainer
	 */
	public Boolean getTrainer() {
		return this.trainer;
	}
	/**
	 * @return the gearId
	 */
	public String getGearId() {
		return this.gearId;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaActivityUpdate [name=" + this.name + ", type=" + this.type + ", privateActivity=" + this.privateActivity + ", commute=" + this.commute
				+ ", trainer=" + this.trainer + ", gearId=" + this.gearId + ", description=" + this.description + "]";
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.commute == null) ? 0 : this.commute.hashCode());
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result + ((this.gearId == null) ? 0 : this.gearId.hashCode());
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + ((this.privateActivity == null) ? 0 : this.privateActivity.hashCode());
		result = prime * result + ((this.trainer == null) ? 0 : this.trainer.hashCode());
		result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaActivityUpdate)) {
			return false;
		}
		StravaActivityUpdate other = (StravaActivityUpdate) obj;
		if (this.commute == null) {
			if (other.commute != null) {
				return false;
			}
		} else if (!this.commute.equals(other.commute)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.gearId == null) {
			if (other.gearId != null) {
				return false;
			}
		} else if (!this.gearId.equals(other.gearId)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.privateActivity == null) {
			if (other.privateActivity != null) {
				return false;
			}
		} else if (!this.privateActivity.equals(other.privateActivity)) {
			return false;
		}
		if (this.trainer == null) {
			if (other.trainer != null) {
				return false;
			}
		} else if (!this.trainer.equals(other.trainer)) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		return true;
	}

}
