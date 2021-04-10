package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.RegisterMentorRequest;
import response.RegisterResponse;
import service.RegisterMentorService;

public class RegisterMentorHandler implements RequestHandler<RegisterMentorRequest, RegisterResponse> {
  @Override
  public RegisterResponse handleRequest(RegisterMentorRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering RegisterStudentHandler");
    logger.log("Username: " + request.getNewMentor().getEmail());
    logger.log("Password : " + request.getNewMentor().getPassword());
    logger.log("Email : " + request.getNewMentor().getEmail());
    return new RegisterMentorService().doRequest(request);
  }
}
