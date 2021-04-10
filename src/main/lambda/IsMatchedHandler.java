package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.IsMatchedRequest;
import response.IsMatchedResponse;
import service.IsMatchedService;

public class IsMatchedHandler implements RequestHandler<IsMatchedRequest, IsMatchedResponse> {
  @Override
  public IsMatchedResponse handleRequest(IsMatchedRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering IsMatchedHandler");
    logger.log("requestedUserId: " + request.getRequestedUserId());
    logger.log("token: " + request.getToken());
    return new IsMatchedService().doRequest(request);
  }
}
