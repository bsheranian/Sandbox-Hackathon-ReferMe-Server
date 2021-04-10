package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.RegisterCompanyRequest;
import request.RegisterStudentRequest;
import response.RegisterResponse;
import service.RegisterCompanyService;
import service.RegisterStudentService;

public class RegisterCompanyHandler implements RequestHandler<RegisterCompanyRequest, RegisterResponse> {
  @Override
  public RegisterResponse handleRequest(RegisterCompanyRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering RegisterCompanyHandler");
    logger.log("Username: " + request.getNewCompany().getEmail());
    logger.log("Password : " + request.getNewCompany().getPassword());
    logger.log("Email : " + request.getNewCompany().getEmail());
    return new RegisterCompanyService().doRequest(request);
  }
}
