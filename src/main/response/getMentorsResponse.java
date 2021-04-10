package response;
import model.Mentor;

public class getMentorsResponse {
    private List<Mentor> mentors;

    public getMentorsResponse() {}

    public getMentorsResponse(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(List<Mentor> mentors) {
        this.mentors = mentors;
    }
}
