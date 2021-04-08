package service;

import dao.AuthDAO;
import dao.UserDAO;
import exception.SandboxLoginException;
import exception.SandboxServerErrorException;
import model.AuthToken;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import request.LoginRequest;
import response.LoginResponse;

import javax.management.RuntimeErrorException;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

  private String username = "username";
  private String password = "password";

  private String HTTP_400 = "[Incorrect Username]";
  private String HTTP_410 = "[Incorrect Password]";
  private String HTTP_500 = "[Server Error]";

  private LoginRequest request = new LoginRequest(username, password);

  @Test
  void doRequest_throwsServerException_with500Response_whenServerFailsToValidateCreds() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    UserDAO mockUserDAO = Mockito.mock(UserDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password)).thenThrow(new RuntimeErrorException(new Error("error")));

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getUserDAO()).thenReturn(mockUserDAO);

    assertThrows(SandboxServerErrorException.class, () -> loginService.doRequest(request));

    try {
      loginService.doRequest(request);
    } catch (Exception e) {
      assertEquals("[Server Error]: Could not validate credentials", e.getMessage());
      assertTrue(e.getMessage().contains(HTTP_500));
    }
  }


  @Test
  void doRequest_throwsLoginException_with400Response_whenIncorrectUsername() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    UserDAO mockUserDAO = Mockito.mock(UserDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password)).thenThrow(new SandboxLoginException("[Incorrect Username]"));

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getUserDAO()).thenReturn(mockUserDAO);

    assertThrows(SandboxLoginException.class, () -> loginService.doRequest(request));

    try {
      loginService.doRequest(request);
    } catch (Exception e) {
      assertEquals("[Incorrect Username]", e.getMessage());
      assertTrue(e.getMessage().contains(HTTP_400));
    }
  }


  @Test
  void doRequest_throwsLoginException_with410Response_whenIncorrectPassword() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    UserDAO mockUserDAO = Mockito.mock(UserDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password)).thenReturn(false);

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getUserDAO()).thenReturn(mockUserDAO);

    assertThrows(SandboxLoginException.class, () -> loginService.doRequest(request));

    try {
      loginService.doRequest(request);
    } catch (Exception e) {
      assertEquals("[Incorrect Password]", e.getMessage());
      assertTrue(e.getMessage().contains(HTTP_410));
    }
  }



  @Test
  void doRequest_doesNotThrowException_with200Response_whenServerFailsValidatesCreds() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    UserDAO mockUserDAO = Mockito.mock(UserDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password)).thenReturn(true);
    Mockito.when(mockAuthDAO.createAuthToken(username)).thenReturn(new AuthToken(username, "token1234"));

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getUserDAO()).thenReturn(mockUserDAO);

    assertDoesNotThrow(() -> loginService.doRequest(request));

    LoginResponse response = loginService.doRequest(request);

    assertTrue(response.isSuccess());
    assertEquals(new AuthToken(username, "token1234") , response.getAuthToken());
  }


  @Test
  void doRequest_throwsServerException_with500Response_whenServerFailsToCreateAuthToken() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    UserDAO mockUserDAO = Mockito.mock(UserDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password)).thenReturn(true);
    Mockito.when(mockAuthDAO.createAuthToken(username)).thenThrow(new RuntimeErrorException(new Error("error")));

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getUserDAO()).thenReturn(mockUserDAO);

    assertThrows(SandboxServerErrorException.class, () -> loginService.doRequest(request));

    try {
      loginService.doRequest(request);
    } catch (Exception e) {
      assertEquals("[Server Error]: Could not create new session", e.getMessage());
      assertTrue(e.getMessage().contains(HTTP_500));
    }
  }


  @Test
  void doRequest_throwsServerException_with500Response_whenServerFailsToValidatePassword() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    UserDAO mockUserDAO = Mockito.mock(UserDAO.class);

    Mockito.when(mockUserDAO.validateUserCredentials(username, password)).thenThrow(new SandboxServerErrorException("[Server Error]: Unable to verify password"));

    LoginService loginService = Mockito.spy(new LoginService());

    Mockito.when(loginService.getAuthDAO()).thenReturn(mockAuthDAO);
    Mockito.when(loginService.getUserDAO()).thenReturn(mockUserDAO);

    assertThrows(SandboxServerErrorException.class, () -> loginService.doRequest(request));

    try {
      loginService.doRequest(request);
    } catch (Exception e) {
      assertEquals("[Server Error]: Unable to verify password", e.getMessage());
      assertTrue(e.getMessage().contains(HTTP_500));
    }
  }
}