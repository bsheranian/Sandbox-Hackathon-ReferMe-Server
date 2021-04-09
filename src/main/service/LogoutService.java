package service;

import exception.SandboxServerErrorException;
import request.LogoutRequest;
import response.LogoutResponse;
import util.HTTPResponse;


public class LogoutService extends ServiceTemplate<LogoutRequest, LogoutResponse> {


  @Override
  public LogoutResponse doRequest(LogoutRequest request) {
    String token = request.getAuthToken().getToken();
    try {
      getAuthDAO().deleteToken(token);
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPResponse.SERVER_ERROR + ": Could not terminate user session");
    }
    return new LogoutResponse(true, "Logout successful");
  }
}
