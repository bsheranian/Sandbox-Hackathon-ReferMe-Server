package request;

public class declineMatchRequest {
    private String matchID;

    public declineMatchRequest() {}

    public declineMatchRequest(String matchID) {
        this.matchID = matchID;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }
}
