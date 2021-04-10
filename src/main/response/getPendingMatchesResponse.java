package response;
import model.User;
public class getPendingMatchesResponse {

    private List<User> pendingMatches;

    public getPendingMatchesResponse() {}

    public getPendingMatchesResponse(List<User> pendingMatches) {
        this.pendingMatches = pendingMatches;
    }

    public List<User> getPendingMatches() {
        return pendingMatches;
    }

    public void setPendingMatches(List<User> pendingMatches) {
        this.pendingMatches = pendingMatches;
    }
}
