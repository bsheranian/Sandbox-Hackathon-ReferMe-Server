package service;

import dao.AuthDAO;
import exception.SandboxServerErrorException;
import model.AuthToken;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import request.LogoutRequest;
import response.LogoutResponse;
import util.SandboxHTTPResponses;

import javax.management.RuntimeErrorException;

import static org.junit.jupiter.api.Assertions.*;

class LogoutServiceTest {

  private String username = "username";
  private String token = "testToken1234";

  private LogoutRequest request = new LogoutRequest(new AuthToken(username, token));

  @Test
  void doRequest_throwsServerException_with500Response_whenServerFailsToDeleteToken() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
    Mockito.doThrow(new RuntimeErrorException(new Error("error"))).when(mockAuthDAO).deleteToken(token);
    LogoutService logoutService = Mockito.spy(new LogoutService());

    Mockito.when(logoutService.getAuthDAO()).thenReturn(mockAuthDAO);

    assertThrows(SandboxServerErrorException.class, () -> logoutService.doRequest(request));

    try {
      logoutService.doRequest(request);
    } catch (Exception e) {
      assertEquals("[Server Error]: Could not terminate user session", e.getMessage());
      assertTrue(e.getMessage().contains(SandboxHTTPResponses.HTTP_500));
    }
  }


  @Test
  void doRequest_returnsWith200Response_whenServerDeletesToken() {
    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);

    LogoutService logoutService = Mockito.spy(new LogoutService());
    Mockito.when(logoutService.getAuthDAO()).thenReturn(mockAuthDAO);

    assertDoesNotThrow(() -> logoutService.doRequest(request));

    LogoutResponse response = logoutService.doRequest(request);
    assertEquals("Logout successful", response.getMessage());
    assertTrue(response.isSuccess());
  }
}