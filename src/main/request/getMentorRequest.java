package request;

public class getMentorRequest {
    private String mentorId;
    private String industry;

    public getMentorRequest() {}

    public getMentorRequest(String mentorId, String industry) {
        this.mentorId = mentorId;
        this.industry = industry;
    }

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
