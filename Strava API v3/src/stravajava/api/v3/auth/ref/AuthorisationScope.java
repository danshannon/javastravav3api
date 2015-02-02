package stravajava.api.v3.auth.ref;


/**
 * <p>view_private and/or write, leave blank for read-only permissions.</p>
 * 
 * @author Dan Shannon
 *
 */
public enum AuthorisationScope {
	VIEW_PRIVATE("view_private","Allow viewing of private data"),
	WRITE("write","Allow creation of data"),
	UNKNOWN("UNKNOWN","Unknown");
	
	private String id;
	
	private String description;
	
	private AuthorisationScope(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}

	public static AuthorisationScope create(String id) {
		for (AuthorisationScope scope : AuthorisationScope.values()) {
			if (scope.getId().equals(id)) {
				return scope;
			}
		}
		return AuthorisationScope.UNKNOWN;
	}

}
