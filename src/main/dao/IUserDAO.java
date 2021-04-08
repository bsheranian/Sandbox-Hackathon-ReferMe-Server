package dao;

import model.User;

public interface IUserDAO {
  public void registerUser(User newUser);
  public boolean validateUserCredentials(String username, String password);
}
