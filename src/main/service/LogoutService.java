package service;

import dao.AuthDAO;
import exception.SandboxServerErrorException;
import request.LogoutRequest;
import response.LogoutResponse;

import java.io.IOException;

public class LogoutService extends ServiceTemplate<LogoutRequest, LogoutResponse> {


  @Override
  public LogoutResponse doRequest(LogoutRequest request) {
    String token = request.getAuthToken().getToken();
    try {
      getAuthDAO().deleteToken(token);
    } catch (Exception e) {
      throw new SandboxServerErrorException("[Server Error]: Could not terminate user session");
    }

    return new LogoutResponse(true, "Logout successful");
  }

  /**
   * Returns an instance of {@link AuthDAO}. Allows mocking of the AuthDAO class
   * for testing purposes. All usages of AuthDAO should get their AuthDAO
   * instance from this method to allow for mocking of the instance.
   *
   * @return the instance.
   */
  AuthDAO getAuthDAO() {
    return new AuthDAO();
  }
}
