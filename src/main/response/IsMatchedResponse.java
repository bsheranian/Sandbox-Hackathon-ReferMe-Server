package response;

public class IsMatchedResponse {
    private boolean areMatched;
    private boolean userRequestedMatch;
    private boolean otherUserRequestedMatch;


    public IsMatchedResponse() {}

    public IsMatchedResponse(boolean areMatched, boolean userRequestedMatch, boolean otherUserRequestedMatch) {
        this.areMatched = areMatched;
        this.userRequestedMatch = userRequestedMatch;
        this.otherUserRequestedMatch = otherUserRequestedMatch;
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

    public boolean getOtherUserRequestedMatch() {
        return otherUserRequestedMatch;
    }

    public void setOtherUserRequestedMatch(boolean otherUserRequestedMatch) {
        this.otherUserRequestedMatch = otherUserRequestedMatch;
    }
}
