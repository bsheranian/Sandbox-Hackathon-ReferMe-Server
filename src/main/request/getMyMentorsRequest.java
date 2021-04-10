package request;

public class getMyMentorsRequest {
    private String last;
    private String token;
    private int limit;
    private String industry;

    public getMyMentorsRequest() {}

    public getMyMentorsRequest(String last, String industry, int limit, String token) {
        this.last = last;
        this.limit = limit;
        this.token = token;
        this.industry = industry;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
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
