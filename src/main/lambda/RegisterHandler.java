package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.RegisterRequest;
import response.RegisterResponse;
import service.RegisterService;

public class RegisterHandler implements RequestHandler<RegisterRequest, RegisterResponse> {
  @Override
  public RegisterResponse handleRequest(RegisterRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering RegisterHandler");
    logger.log("Username: " + request.getNewUser().getUsername());
    logger.log("Password : " + request.getNewUser().getPassword());
    logger.log("Email : " + request.getNewUser().getEmail());
    return new RegisterService().doRequest(request);
  }
}
