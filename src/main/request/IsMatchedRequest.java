package request;

public class IsMatchedRequest {
    private String requestedUserId;
    private String token;

    public IsMatchedRequest() {}

    public IsMatchedRequest(String requestedUserId, String token) {
        this.requestedUserId = requestedUserId;
        this.token = token;
    }

    public String getRequestedUserId() {
        return requestedUserId;
    }

    public void setRequestedUserId(String requestedUserId) {
        this.requestedUserId = requestedUserId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
