package response;
import  model.Mentor;

public class getMentorResponse {
    private Mentor mentor;

    public  getMentorResponse() {}

    public getMentorResponse(Mentor mentor) {
        this.mentor = mentor;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }
}
