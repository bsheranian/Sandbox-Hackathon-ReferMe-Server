package service;

import dao.AuthDAO;
import dao.UserDAO;
import exception.SandboxLoginException;
import exception.SandboxServerErrorException;
import model.AuthToken;
import request.LoginRequest;
import response.LoginResponse;

public class LoginService extends ServiceTemplate<LoginRequest, LoginResponse> {

  @Override
  public LoginResponse doRequest(LoginRequest request) {

    AuthToken newToken;
    String username = request.getUsername();
    String password = request.getPassword();
    boolean validCredentials;

    try {
      validCredentials = getUserDAO().validateUserCredentials(username, password);
    } catch (Exception e) {
      throw new SandboxLoginException("[Incorrect Username]");
    }

    if (!validCredentials) {
      throw new SandboxLoginException("[Incorrect Password]");
    }

    try {
      newToken = getAuthDAO().createAuthToken(username);
    } catch (Exception e) {
      throw new SandboxServerErrorException("[Server Error]: Could not create new session");
    }

    return new LoginResponse(true, newToken);
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

  /**
   * Returns an instance of {@link UserDAO}. Allows mocking of the UserDAO class
   * for testing purposes. All usages of UserDAO should get their UserDAO
   * instance from this method to allow for mocking of the instance.
   *
   * @return the instance.
   */
  UserDAO getUserDAO() {
    return new UserDAO();
  }

}
