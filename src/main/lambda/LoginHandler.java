package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.LoginRequest;
import response.LoginResponse;
import service.LoginService;

/**
 * An AWS lambda function that logs a user in and returns the user object and an auth code for
 * a successful login.
 */
public class LoginHandler implements RequestHandler<LoginRequest, LoginResponse> {

  /**
   * Logs a user in and returns an auth token for the user to use.
   *
   * @param request contains the data required to fulfill the request.
   * @param context the lambda context.
   * @return the response
   */
  @Override
  public LoginResponse handleRequest(LoginRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering LoginHandler");
    logger.log("Username: " + request.getUsername());
    logger.log("Password: " + request.getPassword());
    return new LoginService().doRequest(request);
  }
}