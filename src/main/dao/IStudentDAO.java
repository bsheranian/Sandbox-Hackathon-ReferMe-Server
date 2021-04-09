package dao;

import model.Student;

public interface IStudentDAO {
  public void registerUser(Student newUser);
  public boolean validateUserCredentials(String username, String password);
  public void deleteUser(String username);
  public void updateUser(Student updatedUser);
}
