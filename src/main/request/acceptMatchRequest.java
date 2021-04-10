package request;

public class acceptMatchRequest {
    private String matchID;

    public acceptMatchRequest() {}

    public acceptMatchRequest(String matchID) {
        this.matchID = matchID;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }
}
