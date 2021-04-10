package response;

import model.Student;

import java.util.List;

public class getStudentsResponse {
    private List<Student> students;
    private boolean hasMorePages;

    public getStudentsResponse() {}

    public getStudentsResponse(List<Student> students, boolean hasMorePages) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public boolean getHasMorePages() {
        return hasMorePages;
    }

    public void setHasMorePages(boolean hasMorePages) {
        this.hasMorePages = hasMorePages;
    }
}
