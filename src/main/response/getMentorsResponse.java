package response;
import model.Mentor;

import java.util.List;

public class getMentorsResponse {
    private List<Mentor> mentors;
    private boolean hasMorePages;

    public getMentorsResponse() {}

    public getMentorsResponse(List<Mentor> mentors, boolean hasMorePages) {
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
