package request;

public class recommendStudentRequest {
    private String studentEmail;
    private String mentorEmail;
    private String message;
    private String jobOpeningId;

    public recommendStudentRequest() {}

    public recommendStudentRequest(String studentEmail, String mentorEmail, String message, String jobOpeningId) {
        this.studentEmail = studentEmail;
        this.mentorEmail = mentorEmail;
        this.message = message;
        this.jobOpeningId = jobOpeningId;
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

    public String getJobOpeningId() {
        return jobOpeningId;
    }

    public void setJobOpeningId(String jobOpeningId) {
        this.jobOpeningId = jobOpeningId;
    }
}
