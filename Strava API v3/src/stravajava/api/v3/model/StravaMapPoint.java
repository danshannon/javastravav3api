package stravajava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author dshannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaMapPoint {
	private Float latitude;
	private Float longitude;

	public StravaMapPoint(final Float latitude, final Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
