package javastrava.json.exception;

/**
 * <p>
 * Default exception if there is some issue with JSON serialisation that can't be dealt with
 * </p>
 * 
 * @author Dan Shannon
 * 
 */
public class JsonSerialisationException extends Exception {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param string Error message
	 * @param e Root cause
	 */
	public JsonSerialisationException(final String string, final Exception e) {
		super(string, e);
	}

}
