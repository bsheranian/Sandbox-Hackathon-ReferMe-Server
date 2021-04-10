package model;

import java.util.Objects;

public class Recommendation {
  private String id;
  private String message;
  private String mentorEmail;
  private String studentEmail;
  private String jobOpeningId;

  public Recommendation() {}

  public Recommendation(String id, String message, String mentorEmail, String studentEmail, String jobOpeningId) {
    this.id = id;
    this.message = message;
    this.mentorEmail = mentorEmail;
    this.studentEmail = studentEmail;
    this.jobOpeningId = jobOpeningId;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getJobOpeningId() {
    return jobOpeningId;
  }

  public void setJobOpeningId(String jobOpeningId) {
    this.jobOpeningId = jobOpeningId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Recommendation that = (Recommendation) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(message, that.message) &&
        Objects.equals(mentorEmail, that.mentorEmail) &&
        Objects.equals(studentEmail, that.studentEmail) &&
        Objects.equals(jobOpeningId, that.jobOpeningId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, message, mentorEmail, studentEmail, jobOpeningId);
  }
}
