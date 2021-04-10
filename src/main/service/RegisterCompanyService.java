package service;

import exception.SandboxEmailAlreadyAssociatedWithUserException;
import exception.SandboxServerErrorException;
import model.AuthToken;
import model.Company;
import request.RegisterCompanyRequest;
import response.RegisterResponse;
import util.HTTPRegex;
import util.UserType;

public class RegisterCompanyService extends ServiceTemplate<RegisterCompanyRequest, RegisterResponse> {


  @Override
  public RegisterResponse doRequest(RegisterCompanyRequest request) {
    Company newCompany = request.getNewCompany();
    AuthToken newToken;

    try {
      getCredDAO().registerCredentials(newCompany.getEmail(), newCompany.getPassword(), UserType.COMPANY);
      getCompanyDAO().addCompany(newCompany);
    } catch (SandboxEmailAlreadyAssociatedWithUserException e) {
      throw new SandboxEmailAlreadyAssociatedWithUserException(e.getMessage());
    } catch (SandboxServerErrorException e) {
      throw new SandboxServerErrorException(e.getMessage());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Could not register company");
    }

    try {
      newToken = getAuthDAO().createAuthToken(newCompany.getEmail());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Could not create new session for registered company");
    }

    return new RegisterResponse(true, newToken);
  }
}
