package request;

public class getPendingMatchesRequest {
    private String last;
    private int limit;

    public getPendingMatchesRequest() {}

    public getPendingMatchesRequest(String last, int limit) {
        this.last = last;
        this.limit = limit;
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
}
