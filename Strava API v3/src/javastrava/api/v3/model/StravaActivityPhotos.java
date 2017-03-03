package javastrava.api.v3.model;

/**
 * <p>
 * Photo(s) associated with a specific activity
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public class StravaActivityPhotos implements StravaEntity {
	/**
	 * Primary photo
	 */
	private StravaPhoto primary;
	/**
	 * Total number of photos (instagram + native)
	 */
	private Integer count;

	/**
	 *
	 */
	public StravaActivityPhotos() {
		super();
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
		if (!(obj instanceof StravaActivityPhotos)) {
			return false;
		}
		final StravaActivityPhotos other = (StravaActivityPhotos) obj;
		if (this.count == null) {
			if (other.count != null) {
				return false;
			}
		} else if (!this.count.equals(other.count)) {
			return false;
		}
		if (this.primary == null) {
			if (other.primary != null) {
				return false;
			}
		} else if (!this.primary.equals(other.primary)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return this.count;
	}

	/**
	 * @return the primary
	 */
	public StravaPhoto getPrimary() {
		return this.primary;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.count == null) ? 0 : this.count.hashCode());
		result = (prime * result) + ((this.primary == null) ? 0 : this.primary.hashCode());
		return result;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(final Integer count) {
		this.count = count;
	}

	/**
	 * @param primary
	 *            the primary to set
	 */
	public void setPrimary(final StravaPhoto primary) {
		this.primary = primary;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaActivityPhotos [primary=" + this.primary + ", count=" + this.count + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

}
