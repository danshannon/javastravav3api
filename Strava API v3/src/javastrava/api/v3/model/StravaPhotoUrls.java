package javastrava.api.v3.model;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * URL's for various versions of a specific photo
 * </p>
 * @author Dan Shannon
 *
 */
public class StravaPhotoUrls {
	@SerializedName("0")
	private String url0;
	@SerializedName("100")
	private String url100;
	@SerializedName("600")
	private String url600;
	/**
	 * @return the url0
	 */
	public String getUrl0() {
		return this.url0;
	}
	/**
	 * @param url0 the url0 to set
	 */
	public void setUrl0(final String url0) {
		this.url0 = url0;
	}
	/**
	 * @return the url100
	 */
	public String getUrl100() {
		return this.url100;
	}
	/**
	 * @param url100 the url100 to set
	 */
	public void setUrl100(final String url100) {
		this.url100 = url100;
	}
	/**
	 * @return the url600
	 */
	public String getUrl600() {
		return this.url600;
	}
	/**
	 * @param url600 the url600 to set
	 */
	public void setUrl600(final String url600) {
		this.url600 = url600;
	}
}
