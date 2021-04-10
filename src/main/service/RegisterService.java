package service;

import exception.SandboxServerErrorException;
import exception.SandboxEmailAlreadyAssociatedWithUserException;
import model.AuthToken;
import model.Company;
import model.Mentor;
import model.Student;
import model.User;
import request.RegisterRequest;
import response.RegisterResponse;
import util.HTTPRegex;
import util.UserType;


public class RegisterService extends ServiceTemplate<RegisterRequest, RegisterResponse> {

  @Override
  public RegisterResponse doRequest(RegisterRequest request) {
    User newUser = request.getNewUser();
    AuthToken newToken;

    try {
      getCredDAO().registerCredentials(newUser.getEmail(), newUser.getPassword(), request.getUserType());

      if (request.getUserType() == UserType.STUDENT) {
        Student student = (Student) newUser;
        getStudentDAO().addStudent(student);
      } else if (request.getUserType() == UserType.MENTOR) {
        Mentor mentor = (Mentor) newUser;
        getMentorDAO().addMentor(mentor);
      } else {
        Company company = (Company) newUser;
        getCompanyDAO().addCompany(company);
      }
    } catch (SandboxEmailAlreadyAssociatedWithUserException e) {
      throw new SandboxEmailAlreadyAssociatedWithUserException(e.getMessage());
    } catch (SandboxServerErrorException e) {
      throw new SandboxServerErrorException(e.getMessage());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Could not register user");
    }

    try {
      newToken = getAuthDAO().createAuthToken(newUser.getEmail());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Could not create new session for registered user");
    }

    return new RegisterResponse(true, newToken);
  }



}
