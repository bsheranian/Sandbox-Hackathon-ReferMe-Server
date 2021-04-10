package request;

import model.Student;
import model.User;

public class RegisterRequest {

  private User newUser;
  private int userType;

  public RegisterRequest() {}

  public RegisterRequest(Student newUser, int userType) {
    this.newUser = newUser;
    this.userType = userType;
  }

  public User getNewUser() {
    return newUser;
  }

  public void setNewUser(User newUser) {
    this.newUser = newUser;
  }

  public int getUserType() {
    return userType;
  }

  public void setUserType(int userType) {
    this.userType = userType;
  }
}
