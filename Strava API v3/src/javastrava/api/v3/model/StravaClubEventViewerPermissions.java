package javastrava.api.v3.model;

/**
 * <p>
 * Permissions the authenticated athlete has on a club event
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaClubEventViewerPermissions {
	private Boolean edit;

	/**
	 * @return The edit permission
	 */
	public Boolean getEdit() {
		return this.edit;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.edit == null) ? 0 : this.edit.hashCode());
		return result;
	}

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

}
