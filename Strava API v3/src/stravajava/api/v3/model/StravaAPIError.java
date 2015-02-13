package stravajava.api.v3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class StravaAPIError {
	private String resource;
	private String field;
	private String code;
}
