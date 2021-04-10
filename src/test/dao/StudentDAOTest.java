package dao;

import exception.SandboxLoginException;
import exception.SandboxEmailAlreadyAssociatedWithUserException;
import model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {

  static private StudentDAO studentDAO;
  static private Student userToValidate;
  static private Student userToRegister;

//  @BeforeEach
//  void setUp() {
//    studentDAO = new StudentDAO();
//    userToValidate = new Student("userValidation@gmail.com", "password","Validmir", false);
//    userToRegister = new Student("testRegister@gmail.com", "password", "Regi", true);
//  }
//
//  @AfterEach
//  void tearDown() {
//    studentDAO.deleteStudent(userToValidate.getEmail());
//    studentDAO.deleteStudent(userToRegister.getEmail());
//  }
//
//  @Test
//  void updateUser() {
//    userToValidate.setLastName("Sheranian");
//    userToValidate.setOrganization("BYU");
//    userToValidate.setPhone("805-638-8444");
//    assertDoesNotThrow(() -> studentDAO.updateUser(userToValidate));
//  }
//
//  @Test
//  void registerUser() {
//    assertDoesNotThrow(() -> studentDAO.registerUser(userToRegister));
//    assertThrows(SandboxEmailAlreadyAssociatedWithUserException.class, () -> studentDAO.registerUser(userToRegister));
//  }
//
//  @Test
//  void validateUserCredentials() {
//    studentDAO.registerUser(userToValidate);
//    assertDoesNotThrow(() -> studentDAO.validateUserCredentials(userToValidate.getEmail(), userToValidate.getPassword()));
//    assertTrue(studentDAO.validateUserCredentials(userToValidate.getEmail(), userToValidate.getPassword()));
//    assertThrows(SandboxLoginException.class, () -> studentDAO.validateUserCredentials("invalid", userToValidate.getPassword()));
//    assertFalse(studentDAO.validateUserCredentials(userToValidate.getEmail(), "invalid"));
//
//    try {
//      studentDAO.validateUserCredentials("invalid", userToValidate.getPassword());
//    } catch (Exception e) {
//      assertEquals("[Incorrect Username]", e.getMessage());
//    }
//
//    try {
//      studentDAO.validateUserCredentials(userToValidate.getEmail(), "invalid");
//    } catch (Exception e) {
//      assertEquals("[Incorrect Password]", e.getMessage());
//    }
//  }
}