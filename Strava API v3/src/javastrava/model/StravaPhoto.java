package javastrava.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javastrava.cache.StravaCacheableEntity;
import javastrava.model.reference.StravaPhotoSource;
import javastrava.model.reference.StravaPhotoType;
import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * Photos are external objects associated with an activity. Currently, the only external photo source is Instagram.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaPhoto implements StravaCacheableEntity<Integer> {
	/**
	 * Strava's unique identifier for the photo
	 */
	private Integer id;

	/**
	 * Identifier of the activity to which the photo is attached
	 */
	private Long activityId;

	/**
	 * Current state of this resource on Strava
	 */
	private StravaResourceState resourceState;

	/**
	 * External link to the image
	 */
	private String ref;

	/**
	 * Unique identifier specified by the source
	 */
	private String uid;

	/**
	 * Caption
	 */
	private String caption;

	/**
	 * Photo's source, currently only Instagram
	 */
	private StravaPhotoType type;

	/**
	 * Date and time the photo was uploaded
	 */
	private ZonedDateTime uploadedAt;

	/**
	 * Date and time the photo was linked with Strava
	 */
	private ZonedDateTime createdAt;

	/**
	 * Geographical location of the photo
	 */
	private StravaMapPoint location;

	/**
	 * Source of the photo (Instagram or Strava)
	 */
	private StravaPhotoSource source;

	/**
	 * URL's to use to access the photo
	 */
	private StravaPhotoUrls urls;

	/**
	 * Unique id given to the photo by Instagram / Strava
	 */
	private String uniqueId;

	/**
	 * Photo sizes
	 */
	private StravaPhotoSizes sizes;

	/**
	 * Activity name - returned by listActivityPhotos endpoint
	 */
	private String activityName;

	/**
	 * Local time the photo was created at (no time zone)
	 */
	private LocalDateTime createdAtLocal;

	/**
	 * Athlete identifier
	 */
	private Integer athleteId;

	/**
	 * UNDOCUMENTED
	 */
	private Boolean defaultPhoto;

	/**
	 * UNDOCUMENTED
	 */
	private String usePrimaryPhoto;

	/**
	 * no args constructor
	 */
	public StravaPhoto() {
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
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
		if (this.activityName == null) {
			if (other.activityName != null) {
				return false;
			}
		} else if (!this.activityName.equals(other.activityName)) {
			return false;
		}
		if (this.athleteId == null) {
			if (other.athleteId != null) {
				return false;
			}
		} else if (!this.athleteId.equals(other.athleteId)) {
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
		if (this.createdAtLocal == null) {
			if (other.createdAtLocal != null) {
				return false;
			}
		} else if (!this.createdAtLocal.equals(other.createdAtLocal)) {
			return false;
		}
		if (this.defaultPhoto == null) {
			if (other.defaultPhoto != null) {
				return false;
			}
		} else if (!this.defaultPhoto.equals(other.defaultPhoto)) {
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
		if (this.sizes == null) {
			if (other.sizes != null) {
				return false;
			}
		} else if (!this.sizes.equals(other.sizes)) {
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
		if (this.usePrimaryPhoto == null) {
			if (other.usePrimaryPhoto != null) {
				return false;
			}
		} else if (!this.usePrimaryPhoto.equals(other.usePrimaryPhoto)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the activityId
	 */
	public Long getActivityId() {
		return this.activityId;
	}

	/**
	 * @return the activityName
	 */
	public String getActivityName() {
		return this.activityName;
	}

	/**
	 * @return the athleteId
	 */
	public Integer getAthleteId() {
		return this.athleteId;
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
	 * @return the createdAtLocal
	 */
	public LocalDateTime getCreatedAtLocal() {
		return this.createdAtLocal;
	}

	/**
	 * @return the defaultPhoto
	 */
	public Boolean getDefaultPhoto() {
		return this.defaultPhoto;
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
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @return the sizes
	 */
	public StravaPhotoSizes getSizes() {
		return this.sizes;
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
	 * @return the usePrimaryPhoto
	 */
	public String getUsePrimaryPhoto() {
		return this.usePrimaryPhoto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.activityId == null) ? 0 : this.activityId.hashCode());
		result = (prime * result) + ((this.activityName == null) ? 0 : this.activityName.hashCode());
		result = (prime * result) + ((this.athleteId == null) ? 0 : this.athleteId.hashCode());
		result = (prime * result) + ((this.caption == null) ? 0 : this.caption.hashCode());
		result = (prime * result) + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
		result = (prime * result) + ((this.createdAtLocal == null) ? 0 : this.createdAtLocal.hashCode());
		result = (prime * result) + ((this.defaultPhoto == null) ? 0 : this.defaultPhoto.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.location == null) ? 0 : this.location.hashCode());
		result = (prime * result) + ((this.ref == null) ? 0 : this.ref.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.sizes == null) ? 0 : this.sizes.hashCode());
		result = (prime * result) + ((this.source == null) ? 0 : this.source.hashCode());
		result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
		result = (prime * result) + ((this.uid == null) ? 0 : this.uid.hashCode());
		result = (prime * result) + ((this.uniqueId == null) ? 0 : this.uniqueId.hashCode());
		result = (prime * result) + ((this.uploadedAt == null) ? 0 : this.uploadedAt.hashCode());
		result = (prime * result) + ((this.urls == null) ? 0 : this.urls.hashCode());
		result = (prime * result) + ((this.usePrimaryPhoto == null) ? 0 : this.usePrimaryPhoto.hashCode());
		return result;
	}

	/**
	 * @param activityId
	 *            the activityId to set
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	/**
	 * @param activityName
	 *            the activityName to set
	 */
	public void setActivityName(final String activityName) {
		this.activityName = activityName;
	}

	/**
	 * @param athleteId
	 *            the athleteId to set
	 */
	public void setAthleteId(Integer athleteId) {
		this.athleteId = athleteId;
	}

	/**
	 * @param caption
	 *            the caption to set
	 */
	public void setCaption(final String caption) {
		this.caption = caption;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(final ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @param createdAtLocal
	 *            the createdAtLocal to set
	 */
	public void setCreatedAtLocal(final LocalDateTime createdAtLocal) {
		this.createdAtLocal = createdAtLocal;
	}

	/**
	 * @param defaultPhoto
	 *            the defaultPhoto to set
	 */
	public void setDefaultPhoto(Boolean defaultPhoto) {
		this.defaultPhoto = defaultPhoto;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(final StravaMapPoint location) {
		this.location = location;
	}

	/**
	 * @param ref
	 *            the ref to set
	 */
	public void setRef(final String ref) {
		this.ref = ref;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(final StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param sizes
	 *            the sizes to set
	 */
	public void setSizes(StravaPhotoSizes sizes) {
		this.sizes = sizes;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(final StravaPhotoSource source) {
		this.source = source;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final StravaPhotoType type) {
		this.type = type;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(final String uid) {
		this.uid = uid;
	}

	/**
	 * @param uniqueId
	 *            the uniqueId to set
	 */
	public void setUniqueId(final String uniqueId) {
		this.uniqueId = uniqueId;
	}

	/**
	 * @param uploadedAt
	 *            the uploadedAt to set
	 */
	public void setUploadedAt(final ZonedDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	/**
	 * @param urls
	 *            the urls to set
	 */
	public void setUrls(final StravaPhotoUrls urls) {
		this.urls = urls;
	}

	/**
	 * @param usePrimaryPhoto
	 *            the usePrimaryPhoto to set
	 */
	public void setUsePrimaryPhoto(String usePrimaryPhoto) {
		this.usePrimaryPhoto = usePrimaryPhoto;
	}

	@Override
	public String toString() {
		return "StravaPhoto [id=" + this.id + ", activityId=" + this.activityId + ", resourceState=" + this.resourceState + ", ref=" + this.ref + ", uid=" + this.uid + ", caption=" + this.caption //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ ", type=" + this.type //$NON-NLS-1$
				+ ", uploadedAt=" + this.uploadedAt + ", createdAt=" + this.createdAt + ", location=" + this.location + ", source=" + this.source + ", urls=" + this.urls + ", uniqueId=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				+ this.uniqueId + ", sizes=" + this.sizes //$NON-NLS-1$
				+ ", activityName=" + this.activityName + ", createdAtLocal=" + this.createdAtLocal + ", athleteId=" + this.athleteId + ", defaultPhoto=" + this.defaultPhoto + ", usePrimaryPhoto=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				+ this.usePrimaryPhoto + "]"; //$NON-NLS-1$
	}
}
