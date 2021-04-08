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
    } catch (SandboxUsernameAlreadyTakenException e) {
      throw new SandboxUsernameAlreadyTakenException(e.getMessage());
    } catch (SandboxServerErrorException e) {
      throw new SandboxServerErrorException(e.getMessage());
    } catch (Exception e) {
      throw new SandboxServerErrorException("[Server Error]: Could not register user");
    }

    try {
      newToken = getAuthDAO().createAuthToken(newUser.getUsername());
    } catch (Exception e) {
      throw new SandboxServerErrorException("[Server Error]: Could not create new session for registered user");
    }

    return new RegisterResponse(true, newToken);
  }



}
