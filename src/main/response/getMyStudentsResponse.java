package response;
import model.Student;

public class getMyStudentsResponse {
    private List<Student> students;

    public getMyStudentsResponse() {}

    public getMyStudentsResponse(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
