package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.RequestMatchRequest;
import response.RequestMatchResponse;
import service.RequestMatchService;

public class RequestMatchHandler implements RequestHandler<RequestMatchRequest, RequestMatchResponse> {
  @Override
  public RequestMatchResponse handleRequest(RequestMatchRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering RequestMatchHandler");
    logger.log("requestedUserId: " + request.getRequestedUserId());
    logger.log("token: " + request.getToken());
    return new RequestMatchService().doRequest(request);
  }
}
