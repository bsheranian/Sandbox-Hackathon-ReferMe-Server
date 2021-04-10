package service;

import exception.SandboxEmailAlreadyAssociatedWithUserException;
import exception.SandboxServerErrorException;
import model.AuthToken;
import model.Mentor;
import model.Student;
import request.RegisterMentorRequest;
import response.RegisterResponse;
import util.HTTPRegex;
import util.UserType;

public class RegisterMentorService extends ServiceTemplate<RegisterMentorRequest, RegisterResponse> {
  @Override
  public RegisterResponse doRequest(RegisterMentorRequest request) {
    Mentor newMentor = request.getNewMentor();
    AuthToken newToken;

    try {
      getCredDAO().registerCredentials(newMentor.getEmail(), newMentor.getPassword(), UserType.MENTOR);
      getMentorDAO().addMentor(newMentor);
    } catch (SandboxEmailAlreadyAssociatedWithUserException e) {
      throw new SandboxEmailAlreadyAssociatedWithUserException(e.getMessage());
    } catch (SandboxServerErrorException e) {
      throw new SandboxServerErrorException(e.getMessage());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Could not register mentor");
    }

    try {
      newToken = getAuthDAO().createAuthToken(newMentor.getEmail());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Could not create new session for registered mentor");
    }

    return new RegisterResponse(true, newToken);
  }
}
