package response;

public class isMatchedResponse {
    private Boolean matched;
    private Boolean userAlreadyRequested;
    private Boolean otherAlreadyRequested;

    public isMatchedResponse(){}

    public isMatchedResponse(Boolean matched, Boolean userAlreadyRequested, Boolean otherAlreadyRequested) {
        this.matched = matched;
        this.userAlreadyRequested = userAlreadyRequested;
        this.otherAlreadyRequested = otherAlreadyRequested;
    }

    public Boolean getMatched() {
        return matched;
    }

    public void setMatched(Boolean matched) {
        this.matched = matched;
    }

    public Boolean getUserAlreadyRequested() {
        return userAlreadyRequested;
    }

    public void setUserAlreadyRequested(Boolean userAlreadyRequested) {
        this.userAlreadyRequested = userAlreadyRequested;
    }

    public Boolean getOtherAlreadyRequested() {
        return otherAlreadyRequested;
    }

    public void setOtherAlreadyRequested(Boolean otherAlreadyRequested) {
        this.otherAlreadyRequested = otherAlreadyRequested;
    }
}
