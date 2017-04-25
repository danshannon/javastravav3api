package javastrava.model;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * Permissions the authenticated athlete has on a club event
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaClubEventViewerPermissions implements StravaEntity {
	private Boolean edit;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaClubEventViewerPermissions)) {
			return false;
		}
		final StravaClubEventViewerPermissions other = (StravaClubEventViewerPermissions) obj;
		if (this.edit == null) {
			if (other.edit != null) {
				return false;
			}
		} else if (!this.edit.equals(other.edit)) {
			return false;
		}
		return true;
	}

	/**
	 * @return The edit permission
	 */
	public Boolean getEdit() {
		return this.edit;
	}

	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.edit == null) ? 0 : this.edit.hashCode());
		return result;
	}

	/**
	 * @param edit
	 *            The edit permission to set
	 */
	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	@Override
	public String toString() {
		return "StravaClubEventViewerPermissions [edit=" + this.edit + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
