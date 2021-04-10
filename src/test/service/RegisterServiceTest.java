//package service;
////
////import dao.AuthDAO;
////import dao.StudentDAO;
////import exception.SandboxServerErrorException;
////import exception.SandboxEmailAlreadyAssociatedWithUserException;
////import model.Student;
////import org.junit.jupiter.api.Test;
////import org.mockito.Mockito;
////import request.RegisterRequest;
////import util.HTTPRegex;
////
////import javax.management.RuntimeErrorException;
////
////import static org.junit.jupiter.api.Assertions.*;
//
//class RegisterServiceTest {
//
////  private Student user = new Student("test@gmail.com", "password", "Tester", false);
////
////  private RegisterRequest request = new RegisterRequest(user);
////
////  @Test
////  void doRequest_throwsServerException_with500Response_whenServerFailsToRegisterUser() {
////    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
////    StudentDAO mockUserDAO = Mockito.mock(StudentDAO.class);
////
////    Mockito.doThrow(new RuntimeErrorException(new Error("error"))).when(mockUserDAO).registerUser(request.getNewUser());
////
////    RegisterService registerService = Mockito.spy(new RegisterService());
////
////    Mockito.when(registerService.getAuthDAO()).thenReturn(mockAuthDAO);
////    Mockito.when(registerService.getStudentDAO()).thenReturn(mockUserDAO);
////
////    assertThrows(SandboxServerErrorException.class, () -> registerService.doRequest(request));
////
////    try {
////      registerService.doRequest(request);
////    } catch (Exception e) {
////      assertEquals(HTTPRegex.SERVER_ERROR + ": Could not register user", e.getMessage());
////      assertTrue(e.getMessage().contains(HTTPRegex.SERVER_ERROR));
////    }
////  }
////
////  @Test
////  void doRequest_throwsServerException_with500Response_whenServerIsUnableToHashPassword() {
////    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
////    StudentDAO mockUserDAO = Mockito.mock(StudentDAO.class);
////
////    Mockito.doThrow(new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Unable to hash password")).when(mockUserDAO).registerUser(request.getNewUser());
////
////    RegisterService registerService = Mockito.spy(new RegisterService());
////
////    Mockito.when(registerService.getAuthDAO()).thenReturn(mockAuthDAO);
////    Mockito.when(registerService.getStudentDAO()).thenReturn(mockUserDAO);
////
////    assertThrows(SandboxServerErrorException.class, () -> registerService.doRequest(request));
////
////    try {
////      registerService.doRequest(request);
////    } catch (Exception e) {
////      assertEquals(HTTPRegex.SERVER_ERROR + ": Unable to hash password", e.getMessage());
////      assertTrue(e.getMessage().contains(HTTPRegex.SERVER_ERROR));
////    }
////  }
////
////
////  @Test
////  void doRequest_throwsServerException_with500Response_whenServerIsUnableToCreateAuthToken() {
////    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
////    StudentDAO mockUserDAO = Mockito.mock(StudentDAO.class);
////
////    Mockito.doThrow(new RuntimeException()).when(mockAuthDAO).createAuthToken(request.getNewUser().getEmail());
////
////    RegisterService registerService = Mockito.spy(new RegisterService());
////
////    Mockito.when(registerService.getAuthDAO()).thenReturn(mockAuthDAO);
////    Mockito.when(registerService.getStudentDAO()).thenReturn(mockUserDAO);
////
////    assertThrows(SandboxServerErrorException.class, () -> registerService.doRequest(request));
////
////    try {
////      registerService.doRequest(request);
////    } catch (Exception e) {
////      assertEquals(HTTPRegex.SERVER_ERROR + ": Could not create new session for registered user", e.getMessage());
////      assertTrue(e.getMessage().contains(HTTPRegex.SERVER_ERROR));
////    }
////  }
////
////
////  @Test
////  void doRequest_throwsServerException_with430Response_whenUsernameIsAlreadyTaken() {
////    AuthDAO mockAuthDAO = Mockito.mock(AuthDAO.class);
////    StudentDAO mockUserDAO = Mockito.mock(StudentDAO.class);
////
////    Mockito.doThrow(new SandboxEmailAlreadyAssociatedWithUserException(HTTPRegex.EMAIL_TAKEN))
////        .when(mockUserDAO).registerUser(request.getNewUser());
////
////    RegisterService registerService = Mockito.spy(new RegisterService());
////
////    Mockito.when(registerService.getAuthDAO()).thenReturn(mockAuthDAO);
////    Mockito.when(registerService.getStudentDAO()).thenReturn(mockUserDAO);
////
////    assertThrows(SandboxEmailAlreadyAssociatedWithUserException.class, () -> registerService.doRequest(request));
////
////    try {
////      registerService.doRequest(request);
////    } catch (Exception e) {
////      assertEquals(HTTPRegex.EMAIL_TAKEN, e.getMessage());
////      assertTrue(e.getMessage().contains(HTTPRegex.EMAIL_TAKEN));
////    }
////  }
//
//
//
//
//}