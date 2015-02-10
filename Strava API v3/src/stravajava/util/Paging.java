package stravajava.util;

import stravajava.api.v3.service.Strava;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
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
	
	public Paging() {
		this.page = 1;
		this.pageSize = Strava.DEFAULT_PAGE_SIZE;
	}
	
	public Paging(int page, int pageSize) {
		this.page = validatePage(page);
		this.pageSize = validatePageSize(pageSize);
		this.ignoreLastN = 0;
		this.ignoreFirstN = 0;
	}
	
	public Paging(int page, int pageSize, int ignoreFirstN, int ignoreLastN) {
		this.page = validatePage(page);
		this.pageSize = validatePageSize(pageSize);
		this.ignoreLastN = ignoreLastN;
		this.ignoreFirstN = ignoreFirstN;
	}

	private int validatePageSize(int pageSize) {
		if (pageSize == 0) {
			return Strava.DEFAULT_PAGE_SIZE;
		} 
		return pageSize;
	}

	private int validatePage(int page) {
		if (page == 0) {
			return 1;
		} else {
			return page;
		}
	}
}
