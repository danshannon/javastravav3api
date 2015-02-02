package stravajava.api.v3.service.exception;

/**
 * @author Dan Shannon
 *
 */
public class BadRequestException extends Throwable {

	/**
	 * Default
	 */
	private static final long	serialVersionUID	= 1L;
	
	public BadRequestException(Throwable cause) {
		super(cause);
	}

}
