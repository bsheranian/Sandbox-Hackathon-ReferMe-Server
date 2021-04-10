package request;

public class GetRecommendationRequest {
  private String token;
  private String jobOpengingId;
  private String recommendationId;

  public GetRecommendationRequest() {}

  public GetRecommendationRequest(String token, String jobOpengingId, String recommendationId) {
    this.token = token;
    this.jobOpengingId = jobOpengingId;
    this.recommendationId = recommendationId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getJobOpengingId() {
    return jobOpengingId;
  }

  public void setJobOpengingId(String jobOpengingId) {
    this.jobOpengingId = jobOpengingId;
  }

  public String getRecommendationId() {
    return recommendationId;
  }

  public void setRecommendationId(String recommendationId) {
    this.recommendationId = recommendationId;
  }
}
