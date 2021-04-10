package response;
import model.Student;

public class getStudentResponse {
    private Student student;

    public getStudentResponse() {}

    public getStudentResponse(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
