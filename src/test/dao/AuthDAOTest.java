//package dao;
//
//import model.AuthToken;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//class AuthDAOTest {
//
//  static private AuthDAO authDAO;
//  static private AuthToken authTokenToDelete;
//  static private AuthToken authTokenToValidate;
//  static private AuthToken authTokenAdded;
//
//  @BeforeAll
//  static void setUp() {
//    authDAO = new AuthDAO();
//  }
//
//  @AfterAll
//  static void tearDown() {
//    authDAO.deleteToken(authTokenToDelete.getToken());
//    authDAO.deleteToken(authTokenToValidate.getToken());
//    authDAO.deleteToken(authTokenAdded.getToken());
//  }
//
//  @Test
//  void createAuthToken() {
//    Assertions.assertDoesNotThrow(() -> authTokenAdded = authDAO.createAuthToken("testUsername"));
//  }
//
//  @Test
//  void deleteToken() {
//    authTokenToDelete = authDAO.createAuthToken("shouldNotExist");
//    Assertions.assertDoesNotThrow(() -> authDAO.deleteToken(authTokenToDelete.getToken()));
//  }
//
//  @Test
//  void validateToken() {
//    authTokenToValidate = authDAO.createAuthToken("validToken");
//    Assertions.assertDoesNotThrow(() -> authDAO.validateToken(authTokenToValidate.getToken()));
//  }
//}