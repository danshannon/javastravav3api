package javastrava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.google.gson.annotations.SerializedName;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class StravaPhotoUrls {
	@SerializedName("0")
	private String url0;
}
