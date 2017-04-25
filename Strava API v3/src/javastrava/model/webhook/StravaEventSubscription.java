package javastrava.model.webhook;

import java.time.ZonedDateTime;

import com.google.gson.annotations.SerializedName;

import javastrava.model.StravaEntity;
import javastrava.model.reference.StravaResourceState;
import javastrava.model.webhook.reference.StravaSubscriptionAspectType;
import javastrava.model.webhook.reference.StravaSubscriptionObjectType;

/**
 * The record of a subscription to an event
 *
 * @author Dan Shannon
 *
 */
public class StravaEventSubscription implements StravaEntity {
	/**
	 * Unique identifier of this event subscription
	 */
	private Integer id;

	/**
	 * Application's id, obtained during registration
	 */
	private Integer applicationId;

	/**
	 * Object type included in the subscription
	 */
	@SerializedName("object_type_id")
	private StravaSubscriptionObjectType objectType;

	/**
	 * Aspect type included in the subscription
	 */
	@SerializedName("aspect_type_id")
	private StravaSubscriptionAspectType aspectType;

	/**
	 * Callpack URL which is POSTed to by Strava when an event has occurred
	 */
	@SerializedName("callback_url")
	private String callbackURL;

	/**
	 * Date and time this subscription was created
	 */
	private ZonedDateTime createdAt;

	/**
	 * Date and time this subscription was last updated
	 */
	private ZonedDateTime updatedAt;

	/**
	 * No-argument constructor
	 */
	public StravaEventSubscription() {
		// No args constructor
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
		if (!(obj instanceof StravaEventSubscription)) {
			return false;
		}
		final StravaEventSubscription other = (StravaEventSubscription) obj;
		if (this.applicationId == null) {
			if (other.applicationId != null) {
				return false;
			}
		} else if (!this.applicationId.equals(other.applicationId)) {
			return false;
		}
		if (this.aspectType != other.aspectType) {
			return false;
		}
		if (this.callbackURL == null) {
			if (other.callbackURL != null) {
				return false;
			}
		} else if (!this.callbackURL.equals(other.callbackURL)) {
			return false;
		}
		if (this.createdAt == null) {
			if (other.createdAt != null) {
				return false;
			}
		} else if (!this.createdAt.equals(other.createdAt)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.objectType != other.objectType) {
			return false;
		}
		if (this.updatedAt == null) {
			if (other.updatedAt != null) {
				return false;
			}
		} else if (!this.updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the applicationId
	 */
	public Integer getApplicationId() {
		return this.applicationId;
	}

	/**
	 * @return the aspectType
	 */
	public StravaSubscriptionAspectType getAspectType() {
		return this.aspectType;
	}

	/**
	 * @return the callbackURL
	 */
	public String getCallbackURL() {
		return this.callbackURL;
	}

	/**
	 * @return the createdAt
	 */
	public ZonedDateTime getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the objectType
	 */
	public StravaSubscriptionObjectType getObjectType() {
		return this.objectType;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the updatedAt
	 */
	public ZonedDateTime getUpdatedAt() {
		return this.updatedAt;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.applicationId == null) ? 0 : this.applicationId.hashCode());
		result = (prime * result) + ((this.aspectType == null) ? 0 : this.aspectType.hashCode());
		result = (prime * result) + ((this.callbackURL == null) ? 0 : this.callbackURL.hashCode());
		result = (prime * result) + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.objectType == null) ? 0 : this.objectType.hashCode());
		result = (prime * result) + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
		return result;
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(final Integer applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @param aspectType
	 *            the aspectType to set
	 */
	public void setAspectType(final StravaSubscriptionAspectType aspectType) {
		this.aspectType = aspectType;
	}

	/**
	 * @param callbackURL
	 *            the callbackURL to set
	 */
	public void setCallbackURL(final String callbackURL) {
		this.callbackURL = callbackURL;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(final ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param objectType
	 *            the objectType to set
	 */
	public void setObjectType(final StravaSubscriptionObjectType objectType) {
		this.objectType = objectType;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(final ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaEventSubscription [id=" + this.id + ", applicationId=" + this.applicationId + ", objectType=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ this.objectType + ", aspectType=" //$NON-NLS-1$
				+ this.aspectType + ", callbackURL=" + this.callbackURL + ", createdAt=" + this.createdAt + ", updatedAt=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ this.updatedAt + "]"; //$NON-NLS-1$
	}
}
