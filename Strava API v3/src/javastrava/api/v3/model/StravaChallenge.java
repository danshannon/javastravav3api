package javastrava.api.v3.model;

import java.time.LocalDate;

import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.model.reference.StravaChallengeType;
import javastrava.api.v3.model.reference.StravaMeasurementMethod;
import javastrava.api.v3.model.reference.StravaResourceState;
import javastrava.cache.StravaCacheable;

/**
 * <p>
 * Challenges are the base object used to represent Strava ride and run challenges.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaChallenge implements StravaCacheable<Integer>, StravaEntity {
	/**
	 * Identifier
	 */
	private Integer id;

	private StravaResourceState resourceState;

	private String name;

	private StravaChallengeType type;

	private Boolean joined;

	private String description;

	private String mobileDescription;

	private String additionalInfo;

	private String rules;

	private String prizes;

	private LocalDate startDate;

	private LocalDate endDate;

	private Integer segmentId;

	private StravaActivityType activityType;

	private String dimension;

	private Integer goal;

	private Boolean goalHidden;

	private StravaMeasurementMethod measurementUnit;

	private Integer milestoneCount;

	private String logoUrl;

	private String teaser;

	private String twitterHashtag;

	private String url;

	private String color;

	private Integer participantCount;

	private Float totalDimension;

	private Integer attemptCount;

	private Integer completedCount;

	private Integer leaderboardCount;

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the resourceState
	 */
	@Override
	public StravaResourceState getResourceState() {
		return this.resourceState;
	}

	/**
	 * @param resourceState
	 *            the resourceState to set
	 */
	public void setResourceState(StravaResourceState resourceState) {
		this.resourceState = resourceState;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public StravaChallengeType getType() {
		return this.type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(StravaChallengeType type) {
		this.type = type;
	}

	/**
	 * @return the joined
	 */
	public Boolean getJoined() {
		return this.joined;
	}

	/**
	 * @param joined
	 *            the joined to set
	 */
	public void setJoined(Boolean joined) {
		this.joined = joined;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the mobileDescription
	 */
	public String getMobileDescription() {
		return this.mobileDescription;
	}

	/**
	 * @param mobileDescription
	 *            the mobileDescription to set
	 */
	public void setMobileDescription(String mobileDescription) {
		this.mobileDescription = mobileDescription;
	}

	/**
	 * @return the additionalInfo
	 */
	public String getAdditionalInfo() {
		return this.additionalInfo;
	}

	/**
	 * @param additionalInfo
	 *            the additionalInfo to set
	 */
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	/**
	 * @return the rules
	 */
	public String getRules() {
		return this.rules;
	}

	/**
	 * @param rules
	 *            the rules to set
	 */
	public void setRules(String rules) {
		this.rules = rules;
	}

	/**
	 * @return the prizes
	 */
	public String getPrizes() {
		return this.prizes;
	}

	/**
	 * @param prizes
	 *            the prizes to set
	 */
	public void setPrizes(String prizes) {
		this.prizes = prizes;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return this.startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return this.endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the segmentId
	 */
	public Integer getSegmentId() {
		return this.segmentId;
	}

	/**
	 * @param segmentId
	 *            the segmentId to set
	 */
	public void setSegmentId(Integer segmentId) {
		this.segmentId = segmentId;
	}

	/**
	 * @return the activityType
	 */
	public StravaActivityType getActivityType() {
		return this.activityType;
	}

	/**
	 * @param activityType
	 *            the activityType to set
	 */
	public void setActivityType(StravaActivityType activityType) {
		this.activityType = activityType;
	}

	/**
	 * @return the dimension
	 */
	public String getDimension() {
		return this.dimension;
	}

	/**
	 * @param dimension
	 *            the dimension to set
	 */
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return the goal
	 */
	public Integer getGoal() {
		return this.goal;
	}

	/**
	 * @param goal
	 *            the goal to set
	 */
	public void setGoal(Integer goal) {
		this.goal = goal;
	}

	/**
	 * @return the goalHidden
	 */
	public Boolean getGoalHidden() {
		return this.goalHidden;
	}

	/**
	 * @param goalHidden
	 *            the goalHidden to set
	 */
	public void setGoalHidden(Boolean goalHidden) {
		this.goalHidden = goalHidden;
	}

	/**
	 * @return the measurementUnit
	 */
	public StravaMeasurementMethod getMeasurementUnit() {
		return this.measurementUnit;
	}

	/**
	 * @param measurementUnit
	 *            the measurementUnit to set
	 */
	public void setMeasurementUnit(StravaMeasurementMethod measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	/**
	 * @return the milestoneCount
	 */
	public Integer getMilestoneCount() {
		return this.milestoneCount;
	}

	/**
	 * @param milestoneCount
	 *            the milestoneCount to set
	 */
	public void setMilestoneCount(Integer milestoneCount) {
		this.milestoneCount = milestoneCount;
	}

	/**
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return this.logoUrl;
	}

	/**
	 * @param logoUrl
	 *            the logoUrl to set
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * @return the teaser
	 */
	public String getTeaser() {
		return this.teaser;
	}

	/**
	 * @param teaser
	 *            the teaser to set
	 */
	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}

	/**
	 * @return the twitterHashtag
	 */
	public String getTwitterHashtag() {
		return this.twitterHashtag;
	}

	/**
	 * @param twitterHashtag
	 *            the twitterHashtag to set
	 */
	public void setTwitterHashtag(String twitterHashtag) {
		this.twitterHashtag = twitterHashtag;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the participantCount
	 */
	public Integer getParticipantCount() {
		return this.participantCount;
	}

	/**
	 * @param participantCount
	 *            the participantCount to set
	 */
	public void setParticipantCount(Integer participantCount) {
		this.participantCount = participantCount;
	}

	/**
	 * @return the totalDimension
	 */
	public Float getTotalDimension() {
		return this.totalDimension;
	}

	/**
	 * @param totalDimension
	 *            the totalDimension to set
	 */
	public void setTotalDimension(Float totalDimension) {
		this.totalDimension = totalDimension;
	}

	/**
	 * @return the attemptCount
	 */
	public Integer getAttemptCount() {
		return this.attemptCount;
	}

	/**
	 * @param attemptCount
	 *            the attemptCount to set
	 */
	public void setAttemptCount(Integer attemptCount) {
		this.attemptCount = attemptCount;
	}

	/**
	 * @return the completedCount
	 */
	public Integer getCompletedCount() {
		return this.completedCount;
	}

	/**
	 * @param completedCount
	 *            the completedCount to set
	 */
	public void setCompletedCount(Integer completedCount) {
		this.completedCount = completedCount;
	}

	/**
	 * @return the leaderboardCount
	 */
	public Integer getLeaderboardCount() {
		return this.leaderboardCount;
	}

	/**
	 * @param leaderboardCount
	 *            the leaderboardCount to set
	 */
	public void setLeaderboardCount(Integer leaderboardCount) {
		this.leaderboardCount = leaderboardCount;
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
		result = (prime * result) + ((this.activityType == null) ? 0 : this.activityType.hashCode());
		result = (prime * result) + ((this.additionalInfo == null) ? 0 : this.additionalInfo.hashCode());
		result = (prime * result) + ((this.attemptCount == null) ? 0 : this.attemptCount.hashCode());
		result = (prime * result) + ((this.color == null) ? 0 : this.color.hashCode());
		result = (prime * result) + ((this.completedCount == null) ? 0 : this.completedCount.hashCode());
		result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
		result = (prime * result) + ((this.dimension == null) ? 0 : this.dimension.hashCode());
		result = (prime * result) + ((this.endDate == null) ? 0 : this.endDate.hashCode());
		result = (prime * result) + ((this.goal == null) ? 0 : this.goal.hashCode());
		result = (prime * result) + ((this.goalHidden == null) ? 0 : this.goalHidden.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.joined == null) ? 0 : this.joined.hashCode());
		result = (prime * result) + ((this.leaderboardCount == null) ? 0 : this.leaderboardCount.hashCode());
		result = (prime * result) + ((this.logoUrl == null) ? 0 : this.logoUrl.hashCode());
		result = (prime * result) + ((this.measurementUnit == null) ? 0 : this.measurementUnit.hashCode());
		result = (prime * result) + ((this.milestoneCount == null) ? 0 : this.milestoneCount.hashCode());
		result = (prime * result) + ((this.mobileDescription == null) ? 0 : this.mobileDescription.hashCode());
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.participantCount == null) ? 0 : this.participantCount.hashCode());
		result = (prime * result) + ((this.prizes == null) ? 0 : this.prizes.hashCode());
		result = (prime * result) + ((this.resourceState == null) ? 0 : this.resourceState.hashCode());
		result = (prime * result) + ((this.rules == null) ? 0 : this.rules.hashCode());
		result = (prime * result) + ((this.segmentId == null) ? 0 : this.segmentId.hashCode());
		result = (prime * result) + ((this.startDate == null) ? 0 : this.startDate.hashCode());
		result = (prime * result) + ((this.teaser == null) ? 0 : this.teaser.hashCode());
		result = (prime * result) + ((this.totalDimension == null) ? 0 : this.totalDimension.hashCode());
		result = (prime * result) + ((this.twitterHashtag == null) ? 0 : this.twitterHashtag.hashCode());
		result = (prime * result) + ((this.type == null) ? 0 : this.type.hashCode());
		result = (prime * result) + ((this.url == null) ? 0 : this.url.hashCode());
		return result;
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
		if (!(obj instanceof StravaChallenge)) {
			return false;
		}
		final StravaChallenge other = (StravaChallenge) obj;
		if (this.activityType != other.activityType) {
			return false;
		}
		if (this.additionalInfo == null) {
			if (other.additionalInfo != null) {
				return false;
			}
		} else if (!this.additionalInfo.equals(other.additionalInfo)) {
			return false;
		}
		if (this.attemptCount == null) {
			if (other.attemptCount != null) {
				return false;
			}
		} else if (!this.attemptCount.equals(other.attemptCount)) {
			return false;
		}
		if (this.color == null) {
			if (other.color != null) {
				return false;
			}
		} else if (!this.color.equals(other.color)) {
			return false;
		}
		if (this.completedCount == null) {
			if (other.completedCount != null) {
				return false;
			}
		} else if (!this.completedCount.equals(other.completedCount)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.dimension == null) {
			if (other.dimension != null) {
				return false;
			}
		} else if (!this.dimension.equals(other.dimension)) {
			return false;
		}
		if (this.endDate == null) {
			if (other.endDate != null) {
				return false;
			}
		} else if (!this.endDate.equals(other.endDate)) {
			return false;
		}
		if (this.goal == null) {
			if (other.goal != null) {
				return false;
			}
		} else if (!this.goal.equals(other.goal)) {
			return false;
		}
		if (this.goalHidden == null) {
			if (other.goalHidden != null) {
				return false;
			}
		} else if (!this.goalHidden.equals(other.goalHidden)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.joined == null) {
			if (other.joined != null) {
				return false;
			}
		} else if (!this.joined.equals(other.joined)) {
			return false;
		}
		if (this.leaderboardCount == null) {
			if (other.leaderboardCount != null) {
				return false;
			}
		} else if (!this.leaderboardCount.equals(other.leaderboardCount)) {
			return false;
		}
		if (this.logoUrl == null) {
			if (other.logoUrl != null) {
				return false;
			}
		} else if (!this.logoUrl.equals(other.logoUrl)) {
			return false;
		}
		if (this.measurementUnit != other.measurementUnit) {
			return false;
		}
		if (this.milestoneCount == null) {
			if (other.milestoneCount != null) {
				return false;
			}
		} else if (!this.milestoneCount.equals(other.milestoneCount)) {
			return false;
		}
		if (this.mobileDescription == null) {
			if (other.mobileDescription != null) {
				return false;
			}
		} else if (!this.mobileDescription.equals(other.mobileDescription)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.participantCount == null) {
			if (other.participantCount != null) {
				return false;
			}
		} else if (!this.participantCount.equals(other.participantCount)) {
			return false;
		}
		if (this.prizes == null) {
			if (other.prizes != null) {
				return false;
			}
		} else if (!this.prizes.equals(other.prizes)) {
			return false;
		}
		if (this.resourceState != other.resourceState) {
			return false;
		}
		if (this.rules == null) {
			if (other.rules != null) {
				return false;
			}
		} else if (!this.rules.equals(other.rules)) {
			return false;
		}
		if (this.segmentId == null) {
			if (other.segmentId != null) {
				return false;
			}
		} else if (!this.segmentId.equals(other.segmentId)) {
			return false;
		}
		if (this.startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!this.startDate.equals(other.startDate)) {
			return false;
		}
		if (this.teaser == null) {
			if (other.teaser != null) {
				return false;
			}
		} else if (!this.teaser.equals(other.teaser)) {
			return false;
		}
		if (this.totalDimension == null) {
			if (other.totalDimension != null) {
				return false;
			}
		} else if (!this.totalDimension.equals(other.totalDimension)) {
			return false;
		}
		if (this.twitterHashtag == null) {
			if (other.twitterHashtag != null) {
				return false;
			}
		} else if (!this.twitterHashtag.equals(other.twitterHashtag)) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		if (this.url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!this.url.equals(other.url)) {
			return false;
		}
		return true;
	}

}
