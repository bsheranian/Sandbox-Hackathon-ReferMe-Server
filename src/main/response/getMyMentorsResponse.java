package response;
import model.Mentor;

public class getMyMentorsResponse {
    private List<Mentor> mentors;

    public  getMyMentorsResponse() {}

    public getMyMentorsResponse(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(List<Mentor> mentors) {
        this.mentors = mentors;
    }
}
