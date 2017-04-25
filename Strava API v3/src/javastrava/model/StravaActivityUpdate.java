package javastrava.model;

import com.google.gson.annotations.SerializedName;

import javastrava.api.ActivityAPI;
import javastrava.model.reference.StravaActivityType;
import javastrava.model.reference.StravaResourceState;
import javastrava.service.ActivityService;

/**
 * <p>
 * Collection of fields that can be updated on an activity
 * </p>
 *
 * @see ActivityService#updateActivity(Long, StravaActivityUpdate)
 * @see ActivityAPI#updateActivity(Long, StravaActivityUpdate)
 * @author Dan Shannon
 *
 */
public class StravaActivityUpdate implements StravaEntity {
	/**
	 * Activity name
	 */
	private String				name;
	/**
	 * Activity type
	 */
	private StravaActivityType	type;

	/**
	 * Whether the activity has been flagged by the athlete as private
	 */
	@SerializedName("private")
	private Boolean	privateActivity;
	/**
	 * Whether the activity has been flagged by the athlete as a commute
	 */
	private Boolean	commute;
	/**
	 * Whether the activity has been flagged by the athlete as being done on a stationary trainer
	 */
	private Boolean	trainer;
	/**
	 * Identifier of the gear (i.e. bike or shoes) used for the activity
	 */
	private String	gearId;
	/**
	 * Description of the activity
	 */
	private String	description;

	/**
	 * No args constructor
	 */
	public StravaActivityUpdate() {
		super();
	}

	/**
	 * <p>
	 * Constructor to create a {@link StravaActivityUpdate} from an existing {@link StravaActivity}
	 *
	 * @param activity
	 *            The activity to be used to create the {@link StravaActivityUpdate} from
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
		final StravaActivityUpdate other = (StravaActivityUpdate) obj;
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

	/**
	 * @return the commute
	 */
	public Boolean getCommute() {
		return this.commute;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the gearId
	 */
	public String getGearId() {
		return this.gearId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the privateActivity
	 */
	public Boolean getPrivateActivity() {
		return this.privateActivity;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the trainer
	 */
	public Boolean getTrainer() {
		return this.trainer;
	}

	/**
	 * @return the type
	 */
	public StravaActivityType getType() {
		return this.type;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.commute == null) ? 0 : this.commute.hashCode());
		result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
		result = (prime * result) + ((this.gearId == null) ? 0 : this.gearId.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.privateActivity == null) ? 0 : this.privateActivity.hashCode());
		result = (prime * result) + ((this.trainer == null) ? 0 : this.trainer.hashCode());
		result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}

	/**
	 * @param commute
	 *            the commute to set
	 */
	public void setCommute(final Boolean commute) {
		this.commute = commute;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param gearId
	 *            the gearId to set
	 */
	public void setGearId(final String gearId) {
		this.gearId = gearId;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param privateActivity
	 *            the privateActivity to set
	 */
	public void setPrivateActivity(final Boolean privateActivity) {
		this.privateActivity = privateActivity;
	}

	/**
	 * @param trainer
	 *            the trainer to set
	 */
	public void setTrainer(final Boolean trainer) {
		this.trainer = trainer;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final StravaActivityType type) {
		this.type = type;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaActivityUpdate [name=" + this.name + ", type=" + this.type + ", privateActivity=" + this.privateActivity + ", commute=" + this.commute //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", trainer=" + this.trainer + ", gearId=" + this.gearId + ", description=" + this.description + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

}
