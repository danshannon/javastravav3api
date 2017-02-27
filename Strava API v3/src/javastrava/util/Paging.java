package javastrava.util;

import javastrava.config.StravaConfig;

/**
 * <p>
 * Paging instruction for control of paging in the Strava API
 * </p>
 *
 * @author Dan Shannon
 */
public class Paging {
	/**
	 * Validate the page number requested
	 *
	 * @param page
	 *            Requested page number
	 * @return If requested page number is zero, return 1
	 */
	private static Integer validatePage(final Integer page) {
		if ((page == null) || (page.intValue() == 0)) {
			return Integer.valueOf(1);
		}
		return page;
	}

	/**
	 * Validate the page size requested.
	 *
	 * @param pageSize
	 *            Requested page size
	 * @return If requested page size is zero, return the default page size
	 */
	private static Integer validatePageSize(final Integer pageSize) {
		if ((pageSize == null) || (pageSize.intValue() == 0)) {
			return StravaConfig.DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	/**
	 * <p>
	 * Page number to be retrieved (NB is 1-indexed, so page 1 starts with item 1; page 0 doesn't exist)
	 * </p>
	 */
	private Integer page;

	/**
	 * <p>
	 * Page size (i.e. number of items to be retrieved in a single call).
	 * </p>
	 */
	private Integer pageSize;

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
		this.page = Integer.valueOf(1);
		this.pageSize = StravaConfig.DEFAULT_PAGE_SIZE;
	}

	/**
	 * Constructor allowing page number and size only
	 *
	 * @param page
	 *            Page to be returned. If you specify 0, page 1 will be returned
	 * @param pageSize
	 *            Size of page to be returned. If you specify 0, the default page size will be used.
	 */
	public Paging(final Integer page, final Integer pageSize) {
		this.page = validatePage(page);
		this.pageSize = validatePageSize(pageSize);
		this.ignoreLastN = 0;
		this.ignoreFirstN = 0;
	}

	/**
	 * Constructor allowing full control
	 *
	 * @param page
	 *            Page to be returned. If you specify 0, page 1 will be returned
	 * @param pageSize
	 *            Size of page to be returned. If you specify 0, the default page size will be used.
	 * @param ignoreFirstN
	 *            Ignore the first N items in the returned page
	 * @param ignoreLastN
	 *            Ignore the last N items in the returned page
	 */
	public Paging(final Integer page, final Integer pageSize, final int ignoreFirstN, final int ignoreLastN) {
		this.page = validatePage(page);
		this.pageSize = validatePageSize(pageSize);
		this.ignoreLastN = ignoreLastN;
		this.ignoreFirstN = ignoreFirstN;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Paging)) {
			return false;
		}
		final Paging other = (Paging) obj;
		if (this.ignoreFirstN != other.ignoreFirstN) {
			return false;
		}
		if (this.ignoreLastN != other.ignoreLastN) {
			return false;
		}
		if (this.page == null) {
			if (other.page != null) {
				return false;
			}
		} else if (!this.page.equals(other.page)) {
			return false;
		}
		if (this.pageSize == null) {
			if (other.pageSize != null) {
				return false;
			}
		} else if (!this.pageSize.equals(other.pageSize)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the ignoreFirstN
	 */
	public int getIgnoreFirstN() {
		return this.ignoreFirstN;
	}

	/**
	 * @return the ignoreLastN
	 */
	public int getIgnoreLastN() {
		return this.ignoreLastN;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return this.page;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return this.pageSize;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + this.ignoreFirstN;
		result = (prime * result) + this.ignoreLastN;
		result = (prime * result) + ((this.page == null) ? 0 : this.page.hashCode());
		result = (prime * result) + ((this.pageSize == null) ? 0 : this.pageSize.hashCode());
		return result;
	}

	/**
	 * @param ignoreFirstN
	 *            the ignoreFirstN to set
	 */
	public void setIgnoreFirstN(final int ignoreFirstN) {
		this.ignoreFirstN = ignoreFirstN;
	}

	/**
	 * @param ignoreLastN
	 *            the ignoreLastN to set
	 */
	public void setIgnoreLastN(final int ignoreLastN) {
		this.ignoreLastN = ignoreLastN;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(final Integer page) {
		this.page = page;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(final Integer pageSize) {
		this.pageSize = pageSize;
	}
}
