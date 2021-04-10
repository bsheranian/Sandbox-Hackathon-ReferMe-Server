package service;

import exception.SandboxServerErrorException;
import model.Recommendation;
import request.GetRecommendationRequest;
import response.GetRecommendationResponse;
import util.HTTPRegex;

public class GetRecommendationService extends ServiceTemplate<GetRecommendationRequest, GetRecommendationResponse> {

  @Override
  public GetRecommendationResponse doRequest(GetRecommendationRequest request) {
    Recommendation recommendation;
    try {
      recommendation = getRecommendationDAO().getRecommendation(request.getRecommendationId(), request.getJobOpeningId());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
    }
    return new GetRecommendationResponse(recommendation);
  }
}
