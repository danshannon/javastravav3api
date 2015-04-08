package javastrava.api.v3.model;

/**
 * Error details returned in a {@link StravaResponse} when an error is returned by the API
 * 
 * @author Dan Shannon
 *
 */
public class StravaAPIError {
	/**
	 * No args constructor
	 */
	public StravaAPIError() {
		super();
	}
	private String resource;
	private String field;
	private String code;
	/**
	 * @return the resource
	 */
	public String getResource() {
		return this.resource;
	}
	/**
	 * @param resource the resource to set
	 */
	public void setResource(final String resource) {
		this.resource = resource;
	}
	/**
	 * @return the field
	 */
	public String getField() {
		return this.field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(final String field) {
		this.field = field;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StravaAPIError [resource=" + this.resource + ", field=" + this.field + ", code=" + this.code + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
		result = prime * result + ((this.field == null) ? 0 : this.field.hashCode());
		result = prime * result + ((this.resource == null) ? 0 : this.resource.hashCode());
		return result;
	}
	/* (non-Javadoc)
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
		if (!(obj instanceof StravaAPIError)) {
			return false;
		}
		StravaAPIError other = (StravaAPIError) obj;
		if (this.code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!this.code.equals(other.code)) {
			return false;
		}
		if (this.field == null) {
			if (other.field != null) {
				return false;
			}
		} else if (!this.field.equals(other.field)) {
			return false;
		}
		if (this.resource == null) {
			if (other.resource != null) {
				return false;
			}
		} else if (!this.resource.equals(other.resource)) {
			return false;
		}
		return true;
	}
}
