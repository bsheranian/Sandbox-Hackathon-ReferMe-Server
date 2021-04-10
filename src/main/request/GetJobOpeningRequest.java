package request;

public class GetJobOpeningRequest {

  private String token;
  private String jobOpeningId;
  private String industry;

  public GetJobOpeningRequest() {}

  public GetJobOpeningRequest(String token, String jobOpeningId, String industry) {
    this.token = token;
    this.jobOpeningId = jobOpeningId;
    this.industry = industry;
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

  public void setJobOpeningId(String jobOpeningId) {
    this.jobOpeningId = jobOpeningId;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }
}
