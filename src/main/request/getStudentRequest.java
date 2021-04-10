package request;

public class getStudentRequest {
    private String last;
    private String industry;

    public getStudentRequest(){}

    public getStudentRequest(String last, String industry) {
        this.last = last;
        this.industry = industry;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
