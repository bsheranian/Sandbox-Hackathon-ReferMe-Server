package request;

import model.Student;

public class RegisterRequest {

  private Student newUser;

  public RegisterRequest() {}

  public RegisterRequest(Student newUser) {
    this.newUser = newUser;
  }

  public Student getNewUser() {
    return newUser;
  }

  public void setNewUser(Student newUser) {
    this.newUser = newUser;
  }
}
