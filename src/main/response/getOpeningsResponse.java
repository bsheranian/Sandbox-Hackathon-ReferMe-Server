package response;
import model.JobOpening;

import java.util.List;

public class getOpeningsResponse {
    private List<JobOpening> jobOpenings;
    private boolean hasMorePages;

    public getOpeningsResponse() {}

    public getOpeningsResponse(List<JobOpening> jobOpenings, boolean hasMorePages) {
        this.jobOpenings = jobOpenings;
        this.hasMorePages = hasMorePages;
    }

    public List<JobOpening> getJobOpenings() {
        return jobOpenings;
    }

    public void setJobOpenings(List<JobOpening> jobOpenings) {
        this.jobOpenings = jobOpenings;
    }

    public boolean hasHasMorePages() {
        return hasMorePages;
    }

    public void setHasMorePages(boolean hasMorePages) {
        this.hasMorePages = hasMorePages;
    }
}
