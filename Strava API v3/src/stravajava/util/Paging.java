package stravajava.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import stravajava.api.v3.service.Strava;

/**
 * <p>
 * Paging instruction for control of paging in the Strava API
 * </p>
 * 
 * @author Dan Shannon
 */
@Data
@EqualsAndHashCode
public class Paging {
	/**
	 * <p>
	 * Page number to be retrieved (NB is 1-indexed, so page 1 starts with item 1; page 0 doesn't exist)
	 * </p>
	 */
	private int page;
	/**
	 * <p>
	 * Page size (i.e. number of items to be retrieved in a single call).
	 * </p>
	 */
	private int pageSize;
	/**
	 * <p>
	 * Ignore the last N of the items in the page (this mostly applies to Strava paging)
	 * </p>
	 */
	private int ignoreLastN;

	/**
	 * <p>
	 * Ignore the first N of the items in the page (this mostly applies to Strava paging)
	 * </p>
	 */
	private int ignoreFirstN;

	/**
	 * Default no-argument constructor assumes that you want the first page, and it should be the default size
	 */
	public Paging() {
		this.page = 1;
		this.pageSize = Strava.DEFAULT_PAGE_SIZE;
	}

	/**
	 * Constructor allowing page number and size only
	 * @param page Page to be returned. If you specify 0, page 1 will be returned
	 * @param pageSize Size of page to be returned. If you specify 0, the default page size will be used.
	 */
	public Paging(final int page, final int pageSize) {
		this.page = validatePage(page);
		this.pageSize = validatePageSize(pageSize);
		this.ignoreLastN = 0;
		this.ignoreFirstN = 0;
	}

	/**
	 * Constructor allowing full control
	 * @param page Page to be returned. If you specify 0, page 1 will be returned
	 * @param pageSize Size of page to be returned. If you specify 0, the default page size will be used.
	 * @param ignoreFirstN Ignore the first N items in the returned page
	 * @param ignoreLastN Ignore the last N items in the returned page
	 */
	public Paging(final int page, final int pageSize, final int ignoreFirstN, final int ignoreLastN) {
		this.page = validatePage(page);
		this.pageSize = validatePageSize(pageSize);
		this.ignoreLastN = ignoreLastN;
		this.ignoreFirstN = ignoreFirstN;
	}

	/**
	 * Validate the page size requested.
	 * @param pageSize Requested page size
	 * @return If requested page size is zero, return the default page size
	 */
	private int validatePageSize(final int pageSize) {
		if (pageSize == 0) {
			return Strava.DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	/**
	 * Validate the page number requested
	 * @param page Requested page number
	 * @return If requested page number is zero, return 1
	 */
	private int validatePage(final int page) {
		if (page == 0) {
			return 1;
		} else {
			return page;
		}
	}
}
