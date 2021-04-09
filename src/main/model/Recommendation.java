package model;

import java.util.Objects;

public class Recommendation {
  private String message;
  private String mentorEmail;
  private String studentEmail;

  public Recommendation() {}

  public Recommendation(String message, String mentorEmail, String studentEmail) {
    this.message = message;
    this.mentorEmail = mentorEmail;
    this.studentEmail = studentEmail;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMentorEmail() {
    return mentorEmail;
  }

  public void setMentorEmail(String mentorEmail) {
    this.mentorEmail = mentorEmail;
  }

  public String getStudentEmail() {
    return studentEmail;
  }

  public void setStudentEmail(String studentEmail) {
    this.studentEmail = studentEmail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Recommendation that = (Recommendation) o;
    return Objects.equals(message, that.message) &&
        Objects.equals(mentorEmail, that.mentorEmail) &&
        Objects.equals(studentEmail, that.studentEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, mentorEmail, studentEmail);
  }
}
