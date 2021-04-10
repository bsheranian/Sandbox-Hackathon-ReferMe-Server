package response;
import model.Mentor;

import java.util.List;

public class GetMyMentorsResponse {

    private List<Mentor> mentors;
    private boolean hasMorePages;

    public GetMyMentorsResponse() {}

    public GetMyMentorsResponse(List<Mentor> mentors, boolean hasMorePages) {
        this.mentors = mentors;
        this.hasMorePages = hasMorePages;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    public boolean getHasMorePages() {
        return hasMorePages;
    }

    public void setHasMorePages(boolean hasMorePages) {
        this.hasMorePages = hasMorePages;
    }
}
