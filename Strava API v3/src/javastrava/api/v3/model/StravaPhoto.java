package javastrava.api.v3.model;

import java.time.ZonedDateTime;

import javastrava.api.v3.model.reference.StravaPhotoSource;
import javastrava.api.v3.model.reference.StravaPhotoType;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.cache.StravaCacheable;

/**
 * <p>
 * Photos are external objects associated with an activity. Currently, the only external photo source is Instagram.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaPhoto implements StravaCacheable<Integer> {
	/**
	 * Strava's unique identifier for the photo
	 */
	private Integer				id;
	/**
	 * Identifier of the activity to which the photo is attached
	 */
	private Integer				activityId;
	/**
	 * Current state of this resource on Strava
	 */
	private StravaResourceState	resourceState;
	/**
	 * External link to the image
	 */
	private String				ref;
	/**
	 * Unique identifier specified by the source
	 */
	private String				uid;
	/**
	 * Caption
	 */
	private String				caption;
	/**
	 * Photo's source, currently only Instagram
	 */
	private StravaPhotoType		type;
	/**
	 * Date and time the photo was uploaded
	 */
	private ZonedDateTime				uploadedAt;
	/**
	 * Date and time the photo was linked with Strava
	 */
	private ZonedDateTime				createdAt;
	/**
	 * Location of the photo
	 */
	private StravaMapPoint		location;
	private StravaPhotoSource source;

	private StravaPhotoUrls urls;
	private String uniqueId;
	/**
	 * no args constructor
	 */
	public StravaPhoto() {
		super();
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
		if (!(obj instanceof StravaPhoto)) {
			return false;
		}
		final StravaPhoto other = (StravaPhoto) obj;
		if (this.activityId == null) {
			if (other.activityId != null) {
				return false;
			}
		} else if (!this.activityId.equals(other.activityId)) {
			return false;
		}
		if (this.caption == null) {
			if (other.caption != null) {
				return false;
			}
		} else if (!this.caption.equals(other.caption)) {
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
		if (this.location == null) {
			if (other.location != null) {
				return false;
			}
		} else if (!this.location.equals(other.location)) {
			return false;
		}
		if (this.ref == null) {
			if (other.ref != null) {
				return false;
			}
		} else if (!this.ref.equals(other.ref)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.source != other.source) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		if (this.uid == null) {
			if (other.uid != null) {
				return false;
			}
		} else if (!this.uid.equals(other.uid)) {
			return false;
		}
		if (this.uniqueId == null) {
			if (other.uniqueId != null) {
				return false;
			}
		} else if (!this.uniqueId.equals(other.uniqueId)) {
			return false;
		}
		if (this.uploadedAt == null) {
			if (other.uploadedAt != null) {
				return false;
			}
		} else if (!this.uploadedAt.equals(other.uploadedAt)) {
			return false;
		}
		if (this.urls == null) {
			if (other.urls != null) {
				return false;
			}
		} else if (!this.urls.equals(other.urls)) {
			return false;
		}
		return true;
	}
	/**
	 * @return the activityId
	 */
	public Integer getActivityId() {
		return this.activityId;
	}
	/**
	 * @return the caption
	 */
	public String getCaption() {
		return this.caption;
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
	@Override
	public Integer getId() {
		return this.id;
	}
	/**
	 * @return the location
	 */
	public StravaMapPoint getLocation() {
		return this.location;
	}
	/**
	 * @return the ref
	 */
	public String getRef() {
		return this.ref;
	}
	/**
	 * @return the resourceState
	 */
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}
	/**
	 * @return the source
	 */
	public StravaPhotoSource getSource() {
		return this.source;
	}
	/**
	 * @return the type
	 */
	public StravaPhotoType getType() {
		return this.type;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return this.uid;
	}
	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return this.uniqueId;
	}
	/**
	 * @return the uploadedAt
	 */
	public ZonedDateTime getUploadedAt() {
		return this.uploadedAt;
	}
	/**
	 * @return the urls
	 */
	public StravaPhotoUrls getUrls() {
		return this.urls;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.activityId == null) ? 0 : this.activityId.hashCode());
		result = (prime * result) + ((this.caption == null) ? 0 : this.caption.hashCode());
		result = (prime * result) + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.location == null) ? 0 : this.location.hashCode());
		result = (prime * result) + ((this.ref == null) ? 0 : this.ref.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.source == null) ? 0 : this.source.hashCode());
		result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
		result = (prime * result) + ((this.uid == null) ? 0 : this.uid.hashCode());
		result = (prime * result) + ((this.uniqueId == null) ? 0 : this.uniqueId.hashCode());
		result = (prime * result) + ((this.uploadedAt == null) ? 0 : this.uploadedAt.hashCode());
		result = (prime * result) + ((this.urls == null) ? 0 : this.urls.hashCode());
		return result;
	}
	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(final Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * @param caption the caption to set
	 */
	public void setCaption(final String caption) {
		this.caption = caption;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(final ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(final StravaMapPoint location) {
		this.location = location;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(final String ref) {
		this.ref = ref;
	}
	/**
	 * @param resourceState the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(final StravaPhotoSource source) {
		this.source = source;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(final StravaPhotoType type) {
		this.type = type;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(final String uid) {
		this.uid = uid;
	}
	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(final String uniqueId) {
		this.uniqueId = uniqueId;
	}
	/**
	 * @param uploadedAt the uploadedAt to set
	 */
	public void setUploadedAt(final ZonedDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}
	/**
	 * @param urls the urls to set
	 */
	public void setUrls(final StravaPhotoUrls urls) {
		this.urls = urls;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaPhoto [id=" + this.id + ", activityId=" + this.activityId + ", resourceState=" + this.resourceState + ", ref=" + this.ref + ", uid=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.uid + ", caption=" + this.caption + ", type=" + this.type + ", uploadedAt=" + this.uploadedAt + ", createdAt=" + this.createdAt //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", location=" + this.location + ", source=" + this.source + ", urls=" + this.urls + ", uniqueId=" + this.uniqueId + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}
}
