package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetPendingMatchesRequest;
import response.GetPendingMatchesResponse;
import service.GetPendingMatchesService;

public class GetPendingMatchesHandler implements RequestHandler<GetPendingMatchesRequest, GetPendingMatchesResponse> {
  @Override
  public GetPendingMatchesResponse handleRequest(GetPendingMatchesRequest request, Context context) {

    LambdaLogger logger = context.getLogger();
    logger.log("Entering GetPendingMatchesHandler");
    logger.log("industry: " + request.getIndustry());
    logger.log("last: " + request.getLast());
    logger.log("limit: " + request.getLimit());
    logger.log("token: " + request.getToken());

    return new GetPendingMatchesService().doRequest(request);
  }
}
