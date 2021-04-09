package model;

import java.util.Objects;

public class Student extends User {

  private String school;
  private String major;
  private String industry;
  private float gpa;

  public Student() {}

  public Student(String email, String password, String imageUrl, String name, String school, String major, String industry, float gpa) {
    super(email, password, imageUrl, name);
    this.school = school;
    this.major = major;
    this.industry = industry;
    this.gpa = gpa;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public float getGpa() {
    return gpa;
  }

  public void setGpa(float gpa) {
    this.gpa = gpa;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Student student = (Student) o;
    return Float.compare(student.gpa, gpa) == 0 &&
        Objects.equals(school, student.school) &&
        Objects.equals(major, student.major) &&
        Objects.equals(industry, student.industry);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), school, major, industry, gpa);
  }
}
