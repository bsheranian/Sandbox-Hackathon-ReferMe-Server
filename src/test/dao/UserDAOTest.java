package dao;

import exception.SandboxLoginException;
import exception.SandboxEmailAlreadyAssociatedWithUserException;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

  static private UserDAO userDAO;
  static private User userToValidate;
  static private User userToRegister;

  @BeforeEach
  void setUp() {
    userDAO = new UserDAO();
    userToValidate = new User("userValidation@gmail.com", "password","Validmir", false);
    userToRegister = new User("testRegister@gmail.com", "password", "Regi", true);
  }

  @AfterEach
  void tearDown() {
    userDAO.deleteUser(userToValidate.getEmail());
    userDAO.deleteUser(userToRegister.getEmail());
  }

  @Test
  void updateUser() {
    userToValidate.setLastName("Sheranian");
    userToValidate.setOrganization("BYU");
    userToValidate.setPhone("805-638-8444");
    assertDoesNotThrow(() -> userDAO.updateUser(userToValidate));
  }

  @Test
  void registerUser() {
    assertDoesNotThrow(() -> userDAO.registerUser(userToRegister));
    assertThrows(SandboxEmailAlreadyAssociatedWithUserException.class, () -> userDAO.registerUser(userToRegister));
  }

  @Test
  void validateUserCredentials() {
    userDAO.registerUser(userToValidate);
    assertDoesNotThrow(() -> userDAO.validateUserCredentials(userToValidate.getEmail(), userToValidate.getPassword()));
    assertTrue(userDAO.validateUserCredentials(userToValidate.getEmail(), userToValidate.getPassword()));
    assertThrows(SandboxLoginException.class, () -> userDAO.validateUserCredentials("invalid", userToValidate.getPassword()));
    assertFalse(userDAO.validateUserCredentials(userToValidate.getEmail(), "invalid"));

    try {
      userDAO.validateUserCredentials("invalid", userToValidate.getPassword());
    } catch (Exception e) {
      assertEquals("[Incorrect Username]", e.getMessage());
    }

    try {
      userDAO.validateUserCredentials(userToValidate.getEmail(), "invalid");
    } catch (Exception e) {
      assertEquals("[Incorrect Password]", e.getMessage());
    }
  }
}