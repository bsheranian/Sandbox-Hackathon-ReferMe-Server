package request;

import model.User;

public class RegisterRequest {

  private User newUser;

  public RegisterRequest() {}

  public RegisterRequest(User newUser) {
    this.newUser = newUser;
  }

  public User getNewUser() {
    return newUser;
  }

  public void setNewUser(User newUser) {
    this.newUser = newUser;
  }
}
