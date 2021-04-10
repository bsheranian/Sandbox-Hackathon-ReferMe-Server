package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetJobOpeningsRequest;
import response.GetJobOpeningsResponse;
import service.GetJobOpeningService;
import service.GetJobOpeningsService;

public class GetJobOpeningsHandler implements RequestHandler<GetJobOpeningsRequest, GetJobOpeningsResponse> {
  @Override
  public GetJobOpeningsResponse handleRequest(GetJobOpeningsRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering GetJobOpeningsHandler");
    logger.log("token: " + request.getLast());
    logger.log("limit: " + request.getLimit());
    logger.log("industry: " + request.getIndustry());
    return new GetJobOpeningsService().doRequest(request);
  }
}
