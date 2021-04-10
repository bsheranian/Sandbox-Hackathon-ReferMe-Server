package request;
import model.JobOpening;

public class PostOpeningRequest {
    private JobOpening jobOpening;

    public PostOpeningRequest() {}

    public PostOpeningRequest(JobOpening jobOpening) {
        this.jobOpening = jobOpening;
    }

    public JobOpening getJobOpening() {
        return jobOpening;
    }

    public void setJobOpening(JobOpening jobOpening) {
        this.jobOpening = jobOpening;
    }
}
