package stravajava.util.exception;

/**
 * @author DShannon
 * 
 */
public class JsonSerialisationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param string
	 * @param e
	 */
	public JsonSerialisationException(String string, Exception e) {
		super(string, e);
	}

	/**
	 * @param string
	 */
	public JsonSerialisationException(String string) {
		super(string);
	}

}
