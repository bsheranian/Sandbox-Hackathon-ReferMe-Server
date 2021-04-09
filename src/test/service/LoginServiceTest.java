package service;

import dao.AuthDAO;
import dao.StudentDAO;
import exception.SandboxLoginException;
import exception.SandboxServerErrorException;
import model.AuthToken;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import request.LoginRequest;
import response.LoginResponse;
import util.HTTPRegex;
import javax.management.RuntimeErrorException;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

  private String username = "username";
  private String password = "password";

  private LoginRequest request = new LoginRequest(username, password);

  @Test
  void doRequest_throwsServerException_with500Response_whenServerFailsToValidateCreds() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    StudentDAO mockUserDAO = Mockito.mock(StudentDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password))
        .thenThrow(new RuntimeException());

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getStudentDAO()).thenReturn(mockUserDAO);

    assertThrows(SandboxServerErrorException.class, () -> loginService.doRequest(request));

    try {
      loginService.doRequest(request);
    } catch (Exception e) {
      assertEquals(HTTPRegex.SERVER_ERROR + ": Could not validate credentials", e.getMessage());
      assertTrue(e.getMessage().contains(HTTPRegex.SERVER_ERROR));
    }
  }


  @Test
  void doRequest_throwsLoginException_with400Response_whenIncorrectUsername() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    StudentDAO mockUserDAO = Mockito.mock(StudentDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password))
        .thenThrow(new SandboxLoginException(HTTPRegex.INCORRECT_USERNAME));

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getStudentDAO()).thenReturn(mockUserDAO);

    assertThrows(SandboxLoginException.class, () -> loginService.doRequest(request));

    try {
      loginService.doRequest(request);
    } catch (Exception e) {
      assertEquals(HTTPRegex.INCORRECT_USERNAME, e.getMessage());
      assertTrue(e.getMessage().contains(HTTPRegex.INCORRECT_USERNAME));
    }
  }


  @Test
  void doRequest_throwsLoginException_with410Response_whenIncorrectPassword() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    StudentDAO mockUserDAO = Mockito.mock(StudentDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password)).thenReturn(false);

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getStudentDAO()).thenReturn(mockUserDAO);

    assertThrows(SandboxLoginException.class, () -> loginService.doRequest(request));

    try {
      loginService.doRequest(request);
    } catch (Exception e) {
      assertEquals(HTTPRegex.INCORRECT_PASSWORD, e.getMessage());
      assertTrue(e.getMessage().contains(HTTPRegex.INCORRECT_PASSWORD));
    }
  }



  @Test
  void doRequest_doesNotThrowException_with200Response_whenServerFailsValidatesCreds() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    StudentDAO mockUserDAO = Mockito.mock(StudentDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password)).thenReturn(true);
    Mockito.when(mockAuthDAO.createAuthToken(username)).thenReturn(new AuthToken(username, "token1234"));

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getStudentDAO()).thenReturn(mockUserDAO);

    assertDoesNotThrow(() -> loginService.doRequest(request));

    LoginResponse response = loginService.doRequest(request);

    assertTrue(response.isSuccess());
    assertEquals(new AuthToken(username, "token1234") , response.getAuthToken());
  }


  @Test
  void doRequest_throwsServerException_with500Response_whenServerFailsToCreateAuthToken() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    StudentDAO mockUserDAO = Mockito.mock(StudentDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password)).thenReturn(true);
    Mockito.when(mockAuthDAO.createAuthToken(username)).thenThrow(new RuntimeErrorException(new Error("error")));

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getStudentDAO()).thenReturn(mockUserDAO);

    assertThrows(SandboxServerErrorException.class, () -> loginService.doRequest(request));

    try {
      loginService.doRequest(request);
    } catch (Exception e) {
      assertEquals(HTTPRegex.SERVER_ERROR + ": Could not create new session", e.getMessage());
      assertTrue(e.getMessage().contains(HTTPRegex.SERVER_ERROR));
    }
  }


  @Test
  void doRequest_throwsServerException_with500Response_whenServerFailsToValidatePassword() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    StudentDAO mockUserDAO = Mockito.mock(StudentDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password))
        .thenThrow(new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Unable to verify password"));

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getStudentDAO()).thenReturn(mockUserDAO);

    assertThrows(SandboxServerErrorException.class, () -> loginService.doRequest(request));

    try {
      loginService.doRequest(request);
    } catch (Exception e) {
      assertEquals(HTTPRegex.SERVER_ERROR + ": Unable to verify password", e.getMessage());
      assertTrue(e.getMessage().contains(HTTPRegex.SERVER_ERROR));
    }
  }
}