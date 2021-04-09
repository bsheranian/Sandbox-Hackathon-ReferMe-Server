package service;

import dao.AuthDAO;
import dao.StudentDAO;

public abstract class ServiceTemplate<T, V> {
  public abstract V doRequest(T request);

  /**
   * Returns an instance of {@link AuthDAO}. Allows mocking of the AuthDAO class
   * for testing purposes. All usages of AuthDAO should get their AuthDAO
   * instance from this method to allow for mocking of the instance.
   *
   * @return the instance.
   */
  IAuthDAO getAuthDAO() {
    return new AuthDAO();
  }

  /**
   * Returns an instance of {@link StudentDAO}. Allows mocking of the UserDAO class
   * for testing purposes. All usages of UserDAO should get their UserDAO
   * instance from this method to allow for mocking of the instance.
   *
   * @return the instance.
   */
  IStudentDAO getStudentDAO() {
    return new StudentDAO();
  }
}