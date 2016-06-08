package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.gson.serializer.ClubMembershipStatusSerializer;

public enum StravaClubMembershipStatus implements StravaReferenceType<String> {
	/**
	 * Current club member
	 */
	MEMBER(StravaConfig.string("StravaClubMembershipStatus.member"), Messages.string("StravaClubMembershipStatus.member.description")),
	
	/**
	 * Pending membership approval
	 */
	PENDING(StravaConfig.string("StravaClubMembershipStatus.pending"), Messages.string("StravaClubMembershipStatus.pending.description")),
	
	/**
	 * Unknown status
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description"));

	/**
	 * @param id
	 *            The string representation of the status as returned by
	 *            the Strava API
	 * @return The {@link StravaClubMembershipStatus} with the matching id, or
	 *         {@link StravaClubMembershipStatus#UNKNOWN} if there is no match
	 * @see ClubMembershipStatusSerializer#deserialize(com.google.gson.JsonElement,
	 *      java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public static StravaClubMembershipStatus create(final String id) {
		for (final StravaClubMembershipStatus status : StravaClubMembershipStatus.values()) {
			if (status.getId().equalsIgnoreCase(id)) {
				return status;
			}
		}
		return UNKNOWN;
	}
	
	private String id;
	private String description;
	
	private StravaClubMembershipStatus(String id, String description) {
		this.id = id;
		this.description = description;
	}
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getValue() {
		return this.id;
	}

}
