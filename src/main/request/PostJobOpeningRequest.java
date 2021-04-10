package request;
import model.JobOpening;

public class PostJobOpeningRequest {
    private JobOpening jobOpening;

    public PostJobOpeningRequest() {}

    public PostJobOpeningRequest(JobOpening jobOpening) {
        this.jobOpening = jobOpening;
    }

    public JobOpening getJobOpening() {
        return jobOpening;
    }

    public void setJobOpening(JobOpening jobOpening) {
        this.jobOpening = jobOpening;
    }
}
