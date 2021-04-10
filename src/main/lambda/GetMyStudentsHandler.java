package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetMyStudentsRequest;
import response.GetMyStudentsResponse;
import service.GetMentorService;
import service.GetMyStudentsService;

public class GetMyStudentsHandler implements RequestHandler<GetMyStudentsRequest, GetMyStudentsResponse> {
  @Override
  public GetMyStudentsResponse handleRequest(GetMyStudentsRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering GetMyStudentsHandler");
    logger.log("industry: " + request.getIndustry());
    logger.log("last: " + request.getLast());
    logger.log("limit: " + request.getLimit());
    logger.log("token: " + request.getToken());
    return new GetMyStudentsService().doRequest(request);
  }
}
