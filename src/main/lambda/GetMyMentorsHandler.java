package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.getMyMentorsRequest;
import response.getMyMentorsResponse;
import service.GetMyMentorsService;

public class GetMyMentorsHandler implements RequestHandler<getMyMentorsRequest, getMyMentorsResponse> {
  @Override
  public getMyMentorsResponse handleRequest(getMyMentorsRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering LoginHandler");
    logger.log("last: " + request.getLast());
    logger.log("limit: " + request.getLimit());
    logger.log("token: " + request.getToken());
    logger.log("industry: " + request.getIndustry());
    return new GetMyMentorsService().doRequest(request);
  }
}