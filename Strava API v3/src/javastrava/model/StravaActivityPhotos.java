package javastrava.model;

import javastrava.model.reference.StravaResourceState;

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
	 * UNDOCUMENTED
	 */
	private String usePrimaryPhoto;

	/**
	 *
	 */
	public StravaActivityPhotos() {
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
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
		if (this.usePrimaryPhoto == null) {
			if (other.usePrimaryPhoto != null) {
				return false;
			}
		} else if (!this.usePrimaryPhoto.equals(other.usePrimaryPhoto)) {
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

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the usePrimaryPhoto
	 */
	public String getUsePrimaryPhoto() {
		return this.usePrimaryPhoto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.count == null) ? 0 : this.count.hashCode());
		result = (prime * result) + ((this.primary == null) ? 0 : this.primary.hashCode());
		result = (prime * result) + ((this.usePrimaryPhoto == null) ? 0 : this.usePrimaryPhoto.hashCode());
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
	 * @param usePrimaryPhoto
	 *            the usePrimaryPhoto to set
	 */
	public void setUsePrimaryPhoto(String usePrimaryPhoto) {
		this.usePrimaryPhoto = usePrimaryPhoto;
	}

	@Override
	public String toString() {
		return "StravaActivityPhotos [primary=" + this.primary + ", count=" + this.count + ", usePrimaryPhoto=" + this.usePrimaryPhoto + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

}
