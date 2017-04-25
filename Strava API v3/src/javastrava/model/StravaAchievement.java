package javastrava.model;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * Authenticated user's achievement on a given segment effort
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaAchievement implements StravaEntity {
	/**
	 * Identifier of the achievement type
	 */
	private Integer typeId;

	/**
	 * Type
	 */
	private String	type;
	/**
	 * Rank (1-10 for trophies, 1-3 for PR's)
	 */
	private Integer	rank;

	/**
	 * No-args constructor
	 */
	public StravaAchievement() {
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
		if (!(obj instanceof StravaAchievement)) {
			return false;
		}
		final StravaAchievement other = (StravaAchievement) obj;
		if (this.rank == null) {
			if (other.rank != null) {
				return false;
			}
		} else if (!this.rank.equals(other.rank)) {
			return false;
		}
		if (this.type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!this.type.equals(other.type)) {
			return false;
		}
		if (this.typeId == null) {
			if (other.typeId != null) {
				return false;
			}
		} else if (!this.typeId.equals(other.typeId)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return this.rank;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return this.typeId;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.rank == null) ? 0 : this.rank.hashCode());
		result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
		result = (prime * result) + ((this.typeId == null) ? 0 : this.typeId.hashCode());
		return result;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(final Integer rank) {
		this.rank = rank;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(final Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaAchievement [typeId=" + this.typeId + ", type=" + this.type + ", rank=" + this.rank + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}
