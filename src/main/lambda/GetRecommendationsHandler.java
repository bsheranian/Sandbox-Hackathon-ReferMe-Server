package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetRecommendationsRequest;
import response.GetRecommendationsResponse;
import service.GetRecommendationService;
import service.GetRecommendationsService;

public class GetRecommendationsHandler implements RequestHandler<GetRecommendationsRequest, GetRecommendationsResponse> {
  @Override
  public GetRecommendationsResponse handleRequest(GetRecommendationsRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering GetRecommendationsHandler");
    logger.log("openingId: " + request.getOpeningId());
    logger.log("last: " + request.getLast());
    logger.log("limit: " + request.getLimit());
    return new GetRecommendationsService().doRequest(request);
  }
}
