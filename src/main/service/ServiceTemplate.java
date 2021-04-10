package service;

import dao.AuthDAO;
import dao.CompanyDAO;
import dao.CredDAO;
import dao.JobOpeningDAO;
import dao.MatchDAO;
import dao.MentorDAO;
import dao.RecommendationDAO;
import dao.StudentDAO;
import model.Recommendation;

public abstract class ServiceTemplate<T, V> {
  public abstract V doRequest(T request);

  /**
   * Returns an instance of {@link AuthDAO}. Allows mocking of the AuthDAO class
   * for testing purposes. All usages of AuthDAO should get their AuthDAO
   * instance from this method to allow for mocking of the instance.
   *
   * @return the instance.
   */
  protected AuthDAO getAuthDAO() {
    return new AuthDAO();
  }

  /**
   * Returns an instance of {@link StudentDAO}. Allows mocking of the UserDAO class
   * for testing purposes. All usages of UserDAO should get their UserDAO
   * instance from this method to allow for mocking of the instance.
   *
   * @return the instance.
   */
  protected StudentDAO getStudentDAO() {
    return new StudentDAO();
  }

  protected CompanyDAO getCompanyDAO() { return new CompanyDAO(); }

  protected CredDAO getCredDAO() { return new CredDAO(); }

  protected JobOpeningDAO getJobOpeningDAO() { return new JobOpeningDAO(); }

  protected MatchDAO getMatchDAO() { return new MatchDAO(); }

  protected MentorDAO getMentorDAO() { return new MentorDAO(); }

  protected RecommendationDAO getRecommendationDAO() { return new RecommendationDAO(); }
}