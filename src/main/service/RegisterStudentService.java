package service;


import exception.SandboxServerErrorException;
import exception.SandboxEmailAlreadyAssociatedWithUserException;
import model.AuthToken;
import model.Student;
import request.RegisterStudentRequest;
import response.RegisterResponse;
import util.HTTPRegex;
import util.UserType;


public class RegisterStudentService extends ServiceTemplate<RegisterStudentRequest, RegisterResponse> {

  @Override
  public RegisterResponse doRequest(RegisterStudentRequest request) {
    Student newStudent = request.getNewStudent();
    AuthToken newToken;

    try {
      getCredDAO().registerCredentials(newStudent.getEmail(), newStudent.getPassword(), UserType.STUDENT);
      getStudentDAO().addStudent(newStudent);
    } catch (SandboxEmailAlreadyAssociatedWithUserException e) {
      throw new SandboxEmailAlreadyAssociatedWithUserException(e.getMessage());
    } catch (SandboxServerErrorException e) {
      throw new SandboxServerErrorException(e.getMessage());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Could not register student");
    }

    try {
      newToken = getAuthDAO().createAuthToken(newStudent.getEmail());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Could not create new session for registered student");
    }

    return new RegisterResponse(true, newToken);
  }



}
