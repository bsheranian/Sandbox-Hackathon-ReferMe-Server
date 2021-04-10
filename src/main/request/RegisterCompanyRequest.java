package request;

import model.Company;

public class RegisterCompanyRequest {
  private Company newCompany;

  public RegisterCompanyRequest() {}

  public RegisterCompanyRequest(Company newCompany) {
    this.newCompany = newCompany;
  }

  public Company getNewCompany() {
    return newCompany;
  }

  public void setNewCompany(Company newCompany) {
    this.newCompany = newCompany;
  }
}
