package javastrava.model;

import com.google.gson.annotations.SerializedName;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * URL's for various versions of a specific photo
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaPhotoUrls implements StravaEntity {
	/**
	 * URL of full-size photo
	 */
	@SerializedName("0")
	private String url0;

	/**
	 * URL for thumbnail
	 */
	@SerializedName("100")
	private String url100;

	/**
	 * URL for medium-size rendering
	 */
	@SerializedName("600")
	private String url600;

	/**
	 * No args constructor
	 */
	public StravaPhotoUrls() {
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
		if (!(obj instanceof StravaPhotoUrls)) {
			return false;
		}
		final StravaPhotoUrls other = (StravaPhotoUrls) obj;
		if (this.url0 == null) {
			if (other.url0 != null) {
				return false;
			}
		} else if (!this.url0.equals(other.url0)) {
			return false;
		}
		if (this.url100 == null) {
			if (other.url100 != null) {
				return false;
			}
		} else if (!this.url100.equals(other.url100)) {
			return false;
		}
		if (this.url600 == null) {
			if (other.url600 != null) {
				return false;
			}
		} else if (!this.url600.equals(other.url600)) {
			return false;
		}
		return true;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the url0
	 */
	public String getUrl0() {
		return this.url0;
	}

	/**
	 * @return the url100
	 */
	public String getUrl100() {
		return this.url100;
	}

	/**
	 * @return the url600
	 */
	public String getUrl600() {
		return this.url600;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.url0 == null) ? 0 : this.url0.hashCode());
		result = (prime * result) + ((this.url100 == null) ? 0 : this.url100.hashCode());
		result = (prime * result) + ((this.url600 == null) ? 0 : this.url600.hashCode());
		return result;
	}

	/**
	 * @param url0
	 *            the url0 to set
	 */
	public void setUrl0(final String url0) {
		this.url0 = url0;
	}

	/**
	 * @param url100
	 *            the url100 to set
	 */
	public void setUrl100(final String url100) {
		this.url100 = url100;
	}

	/**
	 * @param url600
	 *            the url600 to set
	 */
	public void setUrl600(final String url600) {
		this.url600 = url600;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaPhotoUrls [url0=" + this.url0 + ", url100=" + this.url100 + ", url600=" + this.url600 + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}
