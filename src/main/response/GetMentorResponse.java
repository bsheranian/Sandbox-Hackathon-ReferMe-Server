package response;
import  model.Mentor;

public class GetMentorResponse {
    private Mentor mentor;

    public GetMentorResponse() {}

    public GetMentorResponse(Mentor mentor) {
        this.mentor = mentor;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }
}
