package response;
import model.Student;

public class GetStudentResponse {
    private Student student;

    public GetStudentResponse() {}

    public GetStudentResponse(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
