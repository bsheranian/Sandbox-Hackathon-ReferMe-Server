package service;

import exception.SandboxServerErrorException;
import exception.SandboxEmailAlreadyAssociatedWithUserException;
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
    } catch (SandboxEmailAlreadyAssociatedWithUserException e) {
      throw new SandboxEmailAlreadyAssociatedWithUserException(e.getMessage());
    } catch (SandboxServerErrorException e) {
      throw new SandboxServerErrorException(e.getMessage());
    } catch (Exception e) {
      throw new SandboxServerErrorException("[Server Error]: Could not register user");
    }

    try {
      newToken = getAuthDAO().createAuthToken(newUser.getEmail());
    } catch (Exception e) {
      throw new SandboxServerErrorException("[Server Error]: Could not create new session for registered user");
    }

    return new RegisterResponse(true, newToken);
  }



}
