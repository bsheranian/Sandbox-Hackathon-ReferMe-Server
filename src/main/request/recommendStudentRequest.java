package request;

public class recommendStudentRequest {
    private String studentEmail;
    private String mentorEmail;
    private String message;

    public recommendStudentRequest() {}

    public recommendStudentRequest(String studentEmail, String mentorEmail, String message) {
        this.studentEmail = studentEmail;
        this.mentorEmail = mentorEmail;
        this.message = message;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
