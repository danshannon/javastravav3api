package javastrava.model;

/**
 * @author Dan Shannon
 *
 */
public class Lap extends AbstractSubActivity {
	private Integer lapNumber;

	/**
	 * @return the lapNumber
	 */
	public Integer getLapNumber() {
		return this.lapNumber;
	}

	/**
	 * @param lapNumber the lapNumber to set
	 */
	public void setLapNumber(final Integer lapNumber) {
		this.lapNumber = lapNumber;
	}
}
