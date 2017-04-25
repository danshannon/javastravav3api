package javastrava.model.reference;

/**
 * @author danshannon
 * @param <T>
 *            The type of the enum's identifier (normally java.lang.String or java.lang.Integer)
 *
 */
public interface StravaReferenceType<T> {
	/**
	 * @return The description of the type instance
	 */
	public String getDescription();

	/**
	 * @return The identifier of the type instance
	 */
	public T getId();

	/**
	 * @return Return the value of the type instance
	 */
	public T getValue();

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString();

}
