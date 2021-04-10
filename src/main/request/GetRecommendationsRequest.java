package request;

public class GetRecommendationsRequest {
    private String openingId;
    private int limit;
    private String last;

    public GetRecommendationsRequest() {}

    public GetRecommendationsRequest(String openingId, int limit, String last) {
        this.openingId = openingId;
        this.limit = limit;
        this.last = last;
    }

    public String getOpeningId() {
        return openingId;
    }

    public void setOpeningId(String openingId) {
        this.openingId = openingId;
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
}
