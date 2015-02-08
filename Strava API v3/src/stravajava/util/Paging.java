package stravajava.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Paging {
	/**
	 * <p>Page number to be retrieved (NB is 1-indexed, so page 1 starts with item 1; page 0 doesn't exist)</p>
	 */
	private int page;
	/**
	 * <p>Page size (i.e. number of items to be retrieved in a single call).</p>
	 */
	private int pageSize;
	/**
	 * <p>Ignore the last N of the items in the page (this mostly applies to Strava paging)</p>
	 */
	private int ignoreLastN;
	
	/**
	 * <p>Ignore the first N of the items in the page (this mostly applies to Strava paging)</p>
	 */
	private int ignoreFirstN;
	
	public Paging(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
		this.ignoreLastN = 0;
		this.ignoreFirstN = 0;
	}
	
	public Paging(int page, int pageSize, int ignoreFirstN, int ignoreLastN) {
		this.page = page;
		this.pageSize = pageSize;
		this.ignoreLastN = ignoreLastN;
		this.ignoreFirstN = ignoreFirstN;
	}
}
