/**
 *
 */
package javastrava.api.v3.model;

/**
 * @author Dan Shannon
 *
 */
public class StravaVideo {
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
	 * No-args constructor
	 */
	public StravaVideo() {
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
	 * @return the stillImageUrl
	 */
	public String getStillImageUrl() {
		return this.stillImageUrl;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.badgeImageUrl == null) ? 0 : this.badgeImageUrl.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.stillImageUrl == null) ? 0 : this.stillImageUrl.hashCode());
		return result;
	}
	/**
	 * @param badgeImageUrl the badgeImageUrl to set
	 */
	public void setBadgeImageUrl(final String badgeImageUrl) {
		this.badgeImageUrl = badgeImageUrl;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}
	/**
	 * @param stillImageUrl the stillImageUrl to set
	 */
	public void setStillImageUrl(final String stillImageUrl) {
		this.stillImageUrl = stillImageUrl;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaVideo [id=" + this.id + ", stillImageUrl=" + this.stillImageUrl + ", badgeImageUrl=" + this.badgeImageUrl + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

}
