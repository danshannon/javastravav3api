package javastrava.model;

import javastrava.model.reference.StravaResourceState;

/**
 * @author Dan Shannon
 *
 */
public class StravaVideo implements StravaEntity {
	/**
	 * Unique id of the video
	 */
	private Integer id;

	/**
	 * URL of still image
	 */
	private String stillImageUrl;

	/**
	 * URL of badge image
	 */
	private String badgeImageUrl;

	/**
	 * Resource state
	 */
	private StravaResourceState resourceState;

	/**
	 * No-args constructor
	 */
	public StravaVideo() {
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
		if (!(obj instanceof StravaVideo)) {
			return false;
		}
		final StravaVideo other = (StravaVideo) obj;
		if (this.badgeImageUrl == null) {
			if (other.badgeImageUrl != null) {
				return false;
			}
		} else if (!this.badgeImageUrl.equals(other.badgeImageUrl)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.stillImageUrl == null) {
			if (other.stillImageUrl != null) {
				return false;
			}
		} else if (!this.stillImageUrl.equals(other.stillImageUrl)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the badgeImageUrl
	 */
	public String getBadgeImageUrl() {
		return this.badgeImageUrl;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @return the stillImageUrl
	 */
	public String getStillImageUrl() {
		return this.stillImageUrl;
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
		result = (prime * result) + ((this.badgeImageUrl == null) ? 0 : this.badgeImageUrl.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.stillImageUrl == null) ? 0 : this.stillImageUrl.hashCode());
		return result;
	}

	/**
	 * @param badgeImageUrl
	 *            the badgeImageUrl to set
	 */
	public void setBadgeImageUrl(final String badgeImageUrl) {
		this.badgeImageUrl = badgeImageUrl;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @param stillImageUrl
	 *            the stillImageUrl to set
	 */
	public void setStillImageUrl(final String stillImageUrl) {
		this.stillImageUrl = stillImageUrl;
	}

	@Override
	public String toString() {
		return "StravaVideo [id=" + this.id + ", stillImageUrl=" + this.stillImageUrl + ", badgeImageUrl=" + this.badgeImageUrl + ", resourceState=" + this.resourceState + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}

}
