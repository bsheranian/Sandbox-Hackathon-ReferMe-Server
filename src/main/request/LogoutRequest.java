package request;

import model.AuthToken;

public class LogoutRequest {

  private AuthToken authToken;

  public LogoutRequest() {}

  public LogoutRequest(AuthToken authToken) {
    this.authToken = authToken;
  }

  public AuthToken getAuthToken() {
    return authToken;
  }

  public void setAuthToken(AuthToken authToken) {
    this.authToken = authToken;
  }
}
