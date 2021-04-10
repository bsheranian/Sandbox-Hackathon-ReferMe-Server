package response;

import model.Student;

public class getStudentsResponse {
    private List<Student> students;

    public getStudentsResponse() {}

    public getStudentsResponse(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
