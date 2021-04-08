package response;

import model.AuthToken;

public class RegisterResponse {

  private boolean success;
  private AuthToken authToken;

  public RegisterResponse() {}

  public RegisterResponse(boolean success, AuthToken authToken) {
    this.success = success;
    this.authToken = authToken;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public AuthToken getAuthToken() {
    return authToken;
  }

  public void setAuthToken(AuthToken authToken) {
    this.authToken = authToken;
  }
}
