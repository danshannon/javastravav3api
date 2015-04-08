package javastrava.model;

/**
 * @author Dan Shannon
 *
 */
public class Photo {
	private String id;
	private Activity activity;
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}
	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return this.activity;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(final Activity activity) {
		this.activity = activity;
	}
}
