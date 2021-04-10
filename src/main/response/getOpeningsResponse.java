package response;
import model.JobOpening;

public class getOpeningsResponse {
    private List<JobOpening> jobOpenings;

    public getOpeningsResponse() {}

    public getOpeningsResponse(List<JobOpening> jobOpenings) {
        this.jobOpenings = jobOpenings;
    }

    public List<JobOpening> getJobOpenings() {
        return jobOpenings;
    }

    public void setJobOpenings(List<JobOpening> jobOpenings) {
        this.jobOpenings = jobOpenings;
    }
}
