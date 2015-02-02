package stravajava.api.v3.model;

import java.util.Date;

import stravajava.api.v3.model.reference.StravaPhotoType;
import stravajava.api.v3.model.reference.StravaResourceState;
import lombok.Data;

/**
 * @author Dan Shannon
 *
 */
@Data
public class StravaPhoto {
	public StravaPhoto() {
		// Required
		super();
	}
	
	private Integer id;
	private Integer activityId;
	private StravaResourceState resourceState;
	private String ref;
	private String uid;
	private String caption;
	private StravaPhotoType type;
	private Date uploadedAt;
	private Date createdAt;
	private StravaMapPoint location;
}
