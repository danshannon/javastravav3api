/**
 *
 */
package javastrava.api.v3.model;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * Photo sizes
 * </p>
 * @author Dan Shannon
 *
 */
public class StravaPhotoSizes {
	/**
	 * URL of full-size photo
	 */
	@SerializedName("0")
	private Integer[] size0;
	/**
	 * URL for thumbnail
	 */
	@SerializedName("100")
	private Integer[] url100;
	/**
	 * URL for medium-size rendering
	 */
	@SerializedName("600")
	private Integer url600;
	/**
	 * No args constructor
	 */
	public StravaPhotoSizes() {
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
		if (!(obj instanceof StravaPhotoSizes)) {
			return false;
		}
		final StravaPhotoSizes other = (StravaPhotoSizes) obj;
		if (!Arrays.equals(this.size0, other.size0)) {
			return false;
		}
		if (!Arrays.equals(this.url100, other.url100)) {
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
	/**
	 * @return the size0
	 */
	public Integer[] getSize0() {
		return this.size0;
	}
	/**
	 * @return the url100
	 */
	public Integer[] getUrl100() {
		return this.url100;
	}
	/**
	 * @return the url600
	 */
	public Integer getUrl600() {
		return this.url600;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + Arrays.hashCode(this.size0);
		result = (prime * result) + Arrays.hashCode(this.url100);
		result = (prime * result) + ((this.url600 == null) ? 0 : this.url600.hashCode());
		return result;
	}
	/**
	 * @param size0 the size0 to set
	 */
	public void setSize0(final Integer[] size0) {
		this.size0 = size0;
	}
	/**
	 * @param url100 the url100 to set
	 */
	public void setUrl100(final Integer[] url100) {
		this.url100 = url100;
	}
	/**
	 * @param url600 the url600 to set
	 */
	public void setUrl600(final Integer url600) {
		this.url600 = url600;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaPhotoSizes [size0=" + Arrays.toString(this.size0) + ", url100=" + Arrays.toString(this.url100) + ", url600=" + this.url600 + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

}
