package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetRecommendationRequest;
import response.GetRecommendationResponse;
import service.GetRecommendationService;

public class GetRecommendationHandler implements RequestHandler<GetRecommendationRequest, GetRecommendationResponse> {
  @Override
  public GetRecommendationResponse handleRequest(GetRecommendationRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering GetRecommendationHandler");
    logger.log("jobOpeningId: " + request.getJobOpengingId());
    logger.log("recommendationId: " + request.getRecommendationId());
    logger.log("token: " + request.getToken());
    return new GetRecommendationService().doRequest(request);
  }
}
