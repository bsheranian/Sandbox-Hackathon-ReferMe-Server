package request;

public class getOpeningsRequest {
    private String last;
    private int limit;
    private String industry;

    public getOpeningsRequest() {}

    public getOpeningsRequest(String last, int limit, String industry) {
        this.last = last;
        this.limit = limit;
        this.industry = industry;
    }

    public String getLast() {
        return last;
    }

    public void setLimit(String last) {
        this.last = last;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
