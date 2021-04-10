package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetRecommendationsRequest;
import response.GetRecommendationsResponse;

public class GetRecommendationsHandler implements RequestHandler<GetRecommendationsRequest, GetRecommendationsResponse> {
  @Override
  public GetRecommendationsResponse handleRequest(GetRecommendationsRequest getRecommendationsRequest, Context context) {
    return null;
  }
}
