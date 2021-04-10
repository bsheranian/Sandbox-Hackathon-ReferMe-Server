package request;

public class getMyStudentsRequest {
    private String last;
    private int limit;
    private String token;
    private String industry;

    public getMyStudentsRequest() {}

    public getMyStudentsRequest(String last, int limit, String token, String industry) {
        this.last = last;
        this.limit = limit;
        this.token = token;
        this.industry = industry;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
