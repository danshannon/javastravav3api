package javastrava.model;

/**
 * @author Dan Shannon
 *
 */
public abstract class AbstractMap {
	private String id;

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
}
