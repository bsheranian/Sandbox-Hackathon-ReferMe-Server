package request;

public class isMatchedRequest {
    private String requestedUserId;
    private String token;

    public isMatchedRequest() {}

    public isMatchedRequest(String requestedUserId, String token) {
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
