package stravajava.api.v3.auth.model;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import stravajava.api.v3.auth.ref.AuthorisationScope;
import stravajava.api.v3.model.StravaAthlete;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Token {
	private StravaAthlete athlete;
	private String token;
	private List<AuthorisationScope> scopes;

	public Token(TokenResponse tokenResponse, AuthorisationScope... scopes) {
		this.athlete = tokenResponse.getAthlete();
		this.token = tokenResponse.getAccessToken();
		this.scopes = Arrays.asList(scopes);
	}
	
}
