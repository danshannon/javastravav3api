package javastrava.model.reference;

import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import javastrava.json.impl.serializer.ClubMembershipStatusSerializer;

/**
 * Club membership status of an athlete - member, pending
 *
 * @author Dan Shannon
 *
 */
public enum StravaClubMembershipStatus implements StravaReferenceType<String> {
	/**
	 * Current club member
	 */
	MEMBER(StravaConfig.string("StravaClubMembershipStatus.member"), Messages.string("StravaClubMembershipStatus.member.description")), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Pending membership approval
	 */
	PENDING(StravaConfig.string("StravaClubMembershipStatus.pending"), Messages.string("StravaClubMembershipStatus.pending.description")), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Unknown status
	 */
	UNKNOWN(StravaConfig.string("Common.unknown"), Messages.string("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

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

	/**
	 * Identifier
	 */
	private String id;
	/**
	 * Description
	 */
	private String description;

	/**
	 * @param id The id
	 * @param description The description
	 */
	private StravaClubMembershipStatus(final String id, final String description) {
		this.id = id;
		this.description = description;
	}
	/**
	 * @see javastrava.model.reference.StravaReferenceType#getDescription()
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**
	 * @see javastrava.model.reference.StravaReferenceType#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * @see javastrava.model.reference.StravaReferenceType#getValue()
	 */
	@Override
	public String getValue() {
		return this.id;
	}

}
