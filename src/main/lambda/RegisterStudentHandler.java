package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.RegisterStudentRequest;
import response.RegisterResponse;
import service.RegisterStudentService;

public class RegisterStudentHandler implements RequestHandler<RegisterStudentRequest, RegisterResponse> {
  @Override
  public RegisterResponse handleRequest(RegisterStudentRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering RegisterStudentHandler");
    logger.log("Username: " + request.getNewStudent().getEmail());
    logger.log("Password : " + request.getNewStudent().getPassword());
    logger.log("Email : " + request.getNewStudent().getEmail());
    return new RegisterStudentService().doRequest(request);
  }
}
