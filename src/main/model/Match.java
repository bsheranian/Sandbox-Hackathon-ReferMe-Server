package model;

import java.util.Objects;

public class Match {
  private String studentId;
  private String mentorId;
  private String status;  //"accepted","id==pending"

  public Match() {}

  public Match(String studentId, String mentorId, String status) {
    this.studentId = studentId;
    this.mentorId = mentorId;
    this.status = status;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getMentorId() {
    return mentorId;
  }

  public void setMentorId(String mentorId) {
    this.mentorId = mentorId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Match match = (Match) o;
    return Objects.equals(studentId, match.studentId) &&
        Objects.equals(mentorId, match.mentorId) &&
        Objects.equals(status, match.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, mentorId, status);
  }
}
