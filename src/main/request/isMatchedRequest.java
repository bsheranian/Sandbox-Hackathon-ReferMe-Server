package request;

public class isMatchedRequest {
    private String requestedUserId;

    public isMatchedRequest() {}

    public isMatchedRequest(String requestedUserId) {
        this.requestedUserId = requestedUserId;
    }

    public String getRequestedUserId() {
        return requestedUserId;
    }

    public void setRequestedUserId(String requestedUserId) {
        this.requestedUserId = requestedUserId;
    }
}
