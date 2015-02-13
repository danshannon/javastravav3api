package stravajava.api.v3.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class StravaResponse {
	private String message;
	private List<StravaAPIError> errors;
}
