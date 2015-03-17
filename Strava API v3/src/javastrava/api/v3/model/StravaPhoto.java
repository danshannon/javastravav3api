package javastrava.api.v3.model;

import java.util.Date;

import javastrava.api.v3.model.reference.StravaPhotoType;
import javastrava.api.v3.model.reference.StravaResourceState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Photos are external objects associated with an activity. Currently, the only external photo source is Instagram.
 * </p>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaPhoto {
	/**
	 * Strava's unique identifier for the photo
	 */
	private Integer				id;
	/**
	 * Identifier of the activity to which the photo is attached
	 */
	private Integer				activityId;
	/**
	 * Current state of this resource on Strava
	 */
	private StravaResourceState	resourceState;
	/**
	 * External link to the image
	 */
	private String				ref;
	/**
	 * Unique identifier specified by the source
	 */
	private String				uid;
	/**
	 * Caption
	 */
	private String				caption;
	/**
	 * Photo's source, currently only Instagram
	 */
	private StravaPhotoType		type;
	/**
	 * Date and time the photo was uploaded
	 */
	private Date				uploadedAt;
	/**
	 * Date and time the photo was linked with Strava
	 */
	private Date				createdAt;
	/**
	 * Location of the photo
	 */
	private StravaMapPoint		location;
	
	private Integer source;
	private StravaPhotoUrls urls;
	private String uniqueId;
}
