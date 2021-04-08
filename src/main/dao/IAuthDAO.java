package dao;

import model.AuthToken;

public interface IAuthDAO {
  public AuthToken createAuthToken(String username);
  public void validateToken(String token);
  public void deleteToken(String token);
}
