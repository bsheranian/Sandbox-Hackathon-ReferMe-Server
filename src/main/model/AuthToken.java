package model;

import java.util.Objects;

public class AuthToken {

  private String username;
  private String token;

  public AuthToken() {}

  public AuthToken(String username, String token) {
    this.username = username;
    this.token = token;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AuthToken authToken = (AuthToken) o;
    return Objects.equals(username, authToken.username) &&
        Objects.equals(token, authToken.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, token);
  }
}
