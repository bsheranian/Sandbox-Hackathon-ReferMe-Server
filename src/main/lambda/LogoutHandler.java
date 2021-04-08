package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.LogoutRequest;
import response.LogoutResponse;
import service.LogoutService;

public class LogoutHandler implements RequestHandler<LogoutRequest, LogoutResponse> {

  @Override
  public LogoutResponse handleRequest(LogoutRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering LogoutHandler");
    logger.log("Username: " + request.getAuthToken().getUsername());
    logger.log("Token: " + request.getAuthToken().getToken());
    return new LogoutService().doRequest(request);
  }
}
