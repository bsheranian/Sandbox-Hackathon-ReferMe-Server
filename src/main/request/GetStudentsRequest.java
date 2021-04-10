package request;

public class GetStudentsRequest {
    private String last;
    private String industry;
    private int limit;

    public GetStudentsRequest() {}

    public GetStudentsRequest(String last, String industry, int limit) {
        this.last = last;
        this.industry = industry;
        this.limit = limit;
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
