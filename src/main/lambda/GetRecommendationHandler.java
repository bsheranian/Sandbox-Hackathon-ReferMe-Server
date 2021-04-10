package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetRecommendationRequest;
import response.GetRecommendationResponse;

public class GetRecommendationHandler implements RequestHandler<GetRecommendationRequest, GetRecommendationResponse> {
  @Override
  public GetRecommendationResponse handleRequest(GetRecommendationRequest getRecommendationRequest, Context context) {
    return null;
  }
}
