package javastrava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * URL's for various versions of a specific photo
 * </p>
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaPhotoUrls {
	@SerializedName("0")
	private String url0;
	@SerializedName("100")
	private String url100;
	@SerializedName("600")
	private String url600;
}
