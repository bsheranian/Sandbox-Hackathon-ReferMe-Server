package response;

import model.JobOpening;

public class GetJobOpeningResponse {
  private JobOpening jobOpening;

  GetJobOpeningResponse() {}

  public GetJobOpeningResponse(JobOpening jobOpening) {
    this.jobOpening = jobOpening;
  }

  public JobOpening getJobOpening() {
    return jobOpening;
  }

  public void setJobOpening(JobOpening jobOpening) {
    this.jobOpening = jobOpening;
  }
}
