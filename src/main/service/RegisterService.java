package service;

import dao.AuthDAO;
import dao.UserDAO;
import exception.SandboxServerErrorException;
import exception.SandboxUsernameAlreadyTakenException;
import model.AuthToken;
import model.User;
import request.RegisterRequest;
import response.RegisterResponse;


public class RegisterService extends ServiceTemplate<RegisterRequest, RegisterResponse> {

  @Override
  public RegisterResponse doRequest(RegisterRequest request) {
    User newUser = request.getNewUser();
    AuthToken newToken;

    try {
      getUserDAO().registerUser(newUser);
    } catch (Exception e) {
      throw new SandboxUsernameAlreadyTakenException("[Username Already Taken]");
    }

    try {
      newToken = getAuthDAO().createAuthToken(newUser.getUsername());
    } catch (Exception e) {
      throw new SandboxServerErrorException("[Server Error]: Could not create new session for registered user");
    }

    return new RegisterResponse(true, newToken);
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
