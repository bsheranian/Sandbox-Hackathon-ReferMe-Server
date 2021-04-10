package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetStudentsRequest;
import response.GetStudentsResponse;
import service.GetStudentsService;

public class GetStudentsHandler implements RequestHandler<GetStudentsRequest, GetStudentsResponse> {
  @Override
  public GetStudentsResponse handleRequest(GetStudentsRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering GetStudentsHandler");
    logger.log("industry: " + request.getIndustry());
    logger.log("last: " + request.getLast());
    logger.log("limit: " + request.getLimit());
    return new GetStudentsService().doRequest(request);
  }
}
