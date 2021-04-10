package request;

public class getMyMentorsRequest {
    private String last;
    private int limit;


    public getMyMentorsRequest() {}

    public getMyMentorsRequest(String last, int limit) {
        this.last = last;
        this.limit = limit;
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
