package request;

import model.Student;
import model.User;

public class RegisterStudentRequest {

  private Student newStudent;

  public RegisterStudentRequest() {}

  public RegisterStudentRequest(Student newStudent) {
    this.newStudent = newStudent;
  }

  public Student getNewStudent() {
    return newStudent;
  }

  public void setNewStudent(Student newStudent) {
    this.newStudent = newStudent;
  }
}
