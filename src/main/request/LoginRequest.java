package request;

public class LoginRequest {
  private String username;
  private String password;
  private int userType;

  public LoginRequest() {}

  public LoginRequest(String username, String password, int userType) {
    this.username = username;
    this.password = password;
    this.userType = userType;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getUserType() {
    return userType;
  }

  public void setUserType(int userType) {
    this.userType = userType;
  }
}
