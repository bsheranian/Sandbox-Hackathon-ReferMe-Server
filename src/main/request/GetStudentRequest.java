package request;

public class GetStudentRequest {
    private String studentId;
    private String industry;

    public GetStudentRequest() {}

    public GetStudentRequest(String studentId, String industry) {
        this.studentId = studentId;
        this.industry = industry;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
