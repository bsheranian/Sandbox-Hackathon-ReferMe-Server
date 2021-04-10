package request;

public class GetRecommendationRequest {
  private String token;
  private String jobOpeningId;
  private String recommendationId;

  public GetRecommendationRequest() {}

  public GetRecommendationRequest(String token, String jobOpengingId, String recommendationId) {
    this.token = token;
    this.jobOpeningId = jobOpengingId;
    this.recommendationId = recommendationId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getJobOpeningId() {
    return jobOpeningId;
  }

  public void setJobOpeningId(String jobOpengingId) {
    this.jobOpeningId = jobOpengingId;
  }

  public String getRecommendationId() {
    return recommendationId;
  }

  public void setRecommendationId(String recommendationId) {
    this.recommendationId = recommendationId;
  }
}
