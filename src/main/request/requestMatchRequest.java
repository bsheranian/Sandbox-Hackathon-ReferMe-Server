package request;

public class requestMatchRequest {
    private String requestedUserId;

    public requestMatchRequest() {}

    public requestMatchRequest(String requestedUserId) {
        this.requestedUserId = requestedUserId;
    }

    public String getRequestedUserId() {
        return requestedUserId;
    }

    public void setRequestedUserId(String requestedUserId) {
        this.requestedUserId = requestedUserId;
    }
}
