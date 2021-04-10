package response;

public class IsMatchedResponse {
    private boolean areMatched;
    private boolean userRequestedMatch;


    public IsMatchedResponse() {}

    public IsMatchedResponse(boolean areMatched, boolean userRequestedMatch) {
        this.areMatched = areMatched;
        this.userRequestedMatch = userRequestedMatch;
    }

    public boolean getAreMatched() {
        return areMatched;
    }

    public void setAreMatched(boolean areMatched) {
        this.areMatched = areMatched;
    }

    public boolean getUserRequestedMatch() {
        return userRequestedMatch;
    }

    public void setUserRequestedMatch(boolean userRequestedMatch) {
        this.userRequestedMatch = userRequestedMatch;
    }
}
