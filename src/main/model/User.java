package model;

import java.util.Objects;

public class User {

  private String email;             //required
  private String password;          //required
  private String organization;
  private String firstName;         //required
  private String lastName;
  private String phone;
  private boolean recruiter;        //required

  public User() {}

  public User(String email, String password, String firstName, boolean recruiter) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.recruiter = recruiter;
    this.organization = "n/a";
    this.lastName = "n/a";
    this.phone = "n/a";
  }

  public User(String email, String password, String organization, String firstName, String lastName, String phone, boolean recruiter) {
    this.email = email;
    this.password = password;
    this.organization = organization;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.recruiter = recruiter;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public boolean isRecruiter() {
    return recruiter;
  }

  public void setRecruiter(boolean recruiter) {
    this.recruiter = recruiter;
  }


}
