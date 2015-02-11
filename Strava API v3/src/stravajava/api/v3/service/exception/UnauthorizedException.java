package stravajava.api.v3.service.exception;

/**
 * @author dshannon
 *
 */
public class UnauthorizedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param cause
	 */
	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param string
	 */
	public UnauthorizedException(String string) {
		super(string);
	}

}
