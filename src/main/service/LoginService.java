package service;

import exception.SandboxLoginException;
import exception.SandboxServerErrorException;
import model.AuthToken;
import request.LoginRequest;
import response.LoginResponse;
import util.HTTPResponse;

public class LoginService extends ServiceTemplate<LoginRequest, LoginResponse> {

  @Override
  public LoginResponse doRequest(LoginRequest request) {

    AuthToken newToken;
    String username = request.getUsername();
    String password = request.getPassword();
    boolean validCredentials;

    try {
      validCredentials = getUserDAO().validateUserCredentials(username, password);
    } catch (SandboxServerErrorException e) {
      throw new SandboxServerErrorException(e.getMessage());
    } catch (SandboxLoginException e) {
      throw new SandboxLoginException(e.getMessage());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPResponse.SERVER_ERROR + ": Could not validate credentials");
    }

    if (!validCredentials) {
      throw new SandboxLoginException(HTTPResponse.INCORRECT_PASSWORD);
    }

    try {
      newToken = getAuthDAO().createAuthToken(username);
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPResponse.SERVER_ERROR + ": Could not create new session");
    }

    return new LoginResponse(true, newToken);
  }
}
