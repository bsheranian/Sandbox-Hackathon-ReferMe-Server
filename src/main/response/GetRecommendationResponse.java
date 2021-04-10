package response;

import model.Recommendation;

public class GetRecommendationResponse {

  private Recommendation recommendation;

  public GetRecommendationResponse() {}

  public GetRecommendationResponse(Recommendation recommendation) {
    this.recommendation = recommendation;
  }

  public Recommendation getRecommendation() {
    return recommendation;
  }

  public void setRecommendation(Recommendation recommendation) {
    this.recommendation = recommendation;
  }
}
