package stravajava.util;


/**
 * @author Dan Shannon
 *
 */
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

	/**
	 * @return the page
	 */
	public int getPage() {
		return this.page;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the ignoreLastN
	 */
	public int getIgnoreLastN() {
		return this.ignoreLastN;
	}

	/**
	 * @param ignoreLastN the ignoreLastN to set
	 */
	public void setIgnoreLastN(int ignoreLastN) {
		this.ignoreLastN = ignoreLastN;
	}

	/**
	 * @return the ignoreFirstN
	 */
	public int getIgnoreFirstN() {
		return this.ignoreFirstN;
	}

	/**
	 * @param ignoreFirstN the ignoreFirstN to set
	 */
	public void setIgnoreFirstN(int ignoreFirstN) {
		this.ignoreFirstN = ignoreFirstN;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Paging [page=" + this.page + ", pageSize=" + this.pageSize + ", ignoreLastN=" + this.ignoreLastN
				+ ", ignoreFirstN=" + this.ignoreFirstN + "]";
	}
	
	
}
