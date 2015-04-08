package javastrava.model;

/**
 * @author Dan Shannon
 *
 */
public class AbstractSubActivity extends AbstractActivity {
	private Activity activity;

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
